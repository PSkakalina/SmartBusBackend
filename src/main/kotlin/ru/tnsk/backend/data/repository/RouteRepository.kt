package ru.tnsk.backend.data.repository

import com.github.benmanes.caffeine.cache.Caffeine
import ru.tnsk.backend.data.db.psql.storage.RouteStorage
import ru.tnsk.backend.domain.model.transport.FullRoute
import ru.tnsk.backend.domain.model.transport.Route
import ru.tnsk.backend.domain.model.transport.TransportType

class RouteRepository(
    private val routeStorage: RouteStorage
) {

    private val findRouteCache = Caffeine.newBuilder()
        .maximumSize(140)
        .build<Int, Route> { routeStorage.findRoute(it) }

    private val allRoutesCache = Caffeine.newBuilder()
        .maximumSize(140)
        .build<Unit, List<Route>> { routeStorage.getAllRoutes() }

    private val findFullRoutesCache = Caffeine.newBuilder()
        .maximumSize(140)
        .build<Int, FullRoute> { routeStorage.findFullRoute(it, true) }

    fun getRoutes(): List<Route> {
        return allRoutesCache.get(Unit)
    }

    fun findRoute(id: Int): Route? = findRouteCache.get(id)

    fun findFullRoute(id: Int): FullRoute? = findFullRoutesCache.get(id)

    fun findRoute(route: String, transportType: TransportType): Route? =
        routeStorage.findRoute(transportType, route)

    fun findRoutes(route: String, transportType: TransportType? = null): List<Route> {
        return transportType?.let {
            routeStorage.findRoutes(route, it)
        } ?: routeStorage.findRoutes(route)
    }
}