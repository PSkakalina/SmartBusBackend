package ru.tnsk.backend.data.db.mappers


import ru.tnsk.backend.core.utils.asDateTimeTz
import ru.tnsk.backend.data.db.psql.entity.TransportHistoryEntity
import ru.tnsk.backend.domain.model.common.LatLng
import ru.tnsk.backend.domain.model.transport.Transport

fun TransportHistoryEntity.asTransport() = Transport(
    name,
    routeName,
    routeId.value,
    transportType,
    graph,
    direction,
    LatLng(lat, lng),
    azimuth,
    timeNav.asDateTimeTz(timeZoneOffset),
    idTypetr,
    rasp,
    speed,
    segmentOrder,
    ramp
)