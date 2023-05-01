package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.RouteEntity
import ru.tnsk.backend.domain.model.transport.FullRoute

fun RouteEntity.asFullRoute(withStops: Boolean) = FullRoute(
    asRoute(),
    traces.map { it.asTrace(withStops) }
)