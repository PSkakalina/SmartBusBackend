package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.RouteEntity
import ru.tnsk.backend.domain.model.transport.Route

fun RouteEntity.asRoute() = Route(
    id.value,
    route,
    name,
    transportType,
    firstStop.asStop(),
    lastStop.asStop()
)