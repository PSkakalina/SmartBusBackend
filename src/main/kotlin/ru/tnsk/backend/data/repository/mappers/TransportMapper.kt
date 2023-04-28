package ru.tnsk.backend.data.repository.mappers

import ru.tnsk.backend.data.network.models.MarkersResponse
import ru.tnsk.backend.domain.model.common.LatLng
import ru.tnsk.backend.domain.model.transport.Transport
import ru.tnsk.backend.domain.model.transport.TransportType

fun MarkersResponse.Marker.asTransport(
    transportType: TransportType,
    routeId: Int
) = Transport(
    title,
    route,
    routeId,
    transportType,
    graph,
    direction,
    LatLng(lat, lng),
    azimuth,
    timeNav,
    idTypetr,
    rasp,
    speed,
    segmentOrder,
    ramp
)