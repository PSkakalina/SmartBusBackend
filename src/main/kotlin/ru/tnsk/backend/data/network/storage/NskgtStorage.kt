package ru.tnsk.backend.data.network.storage

import ru.tnsk.backend.data.network.api.NskgtApi
import ru.tnsk.backend.data.network.models.MarkersResponse
import ru.tnsk.backend.data.network.models.RouteTypeDto
import ru.tnsk.backend.data.network.models.TracesResponse
import ru.tnsk.backend.domain.model.transport.TransportType

class NskgtStorage(private val api: NskgtApi) {
    suspend fun getMarkers(transportType: TransportType, route: String): MarkersResponse {
        return api.getMarkers(createQuery(transportType, route))
    }

    suspend fun getAllRoutes(): List<RouteTypeDto> = api.getAllRoutes()

    suspend fun getTraces(transportType: TransportType, route: String): TracesResponse {
        return api.getTraces(createQuery(transportType, route))
    }

    private fun createQuery(transportType: TransportType, route: String) = "${transportType.value + 1}-$route-W"
}