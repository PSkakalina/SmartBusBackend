package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.StopEntity
import ru.tnsk.backend.domain.model.common.LatLng
import ru.tnsk.backend.domain.model.transport.Stop

fun StopEntity.asStop() = Stop(
    id.value,
    name,
    LatLng(lat, lng),
    len
)