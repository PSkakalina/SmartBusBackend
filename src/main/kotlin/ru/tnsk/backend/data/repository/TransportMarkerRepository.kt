package ru.tnsk.backend.data.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.tnsk.backend.data.network.storage.NskgtStorage
import ru.tnsk.backend.data.repository.mappers.asTransport
import ru.tnsk.backend.domain.model.transport.Transport

class TransportMarkerRepository(
    private val nskgtStorage: NskgtStorage,
    private val routeRepository: RouteRepository
) {

    private var cachedMarkers: List<Transport>? = null

    private val mutex = Mutex()

    suspend fun getAllMarkers(forceUpdate: Boolean): List<Transport> {
        return if (forceUpdate) {
            getAllMarkers()
        } else {
            cachedMarkers ?: getAllMarkers()
        }
    }

    suspend fun updateMarkers() {
        getAllMarkers()
    }

    private suspend fun getAllMarkers(): List<Transport> {
        return buildList<Transport> {
            routeRepository.getRoutes()
                .groupBy { it.transportType }
                .entries
                .forEach { (transportType, routes) ->
                    routes
                        .chunked(5)
                        .forEach { chunk ->
                            val query = chunk.map { NskgtStorage.RouteQuery(it.transportType, it.route) }

                            this += nskgtStorage.getMarkers(query).markers
                                .groupBy { it.route }
                                .entries
                                .flatMap { (routeNumber, markers) ->
                                    val route = chunk.find { it.route == routeNumber } ?: return@flatMap listOf()

                                    markers.map {
                                        it.asTransport(transportType, route.id)
                                    }
                                }
                        }
                }
        }.also {
            mutex.withLock {
                cachedMarkers = it
            }
        }
    }
}