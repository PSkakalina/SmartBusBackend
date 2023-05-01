package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.TraceEntity
import ru.tnsk.backend.domain.model.common.LatLng
import ru.tnsk.backend.domain.model.transport.Trace

fun TraceEntity.asTrace(full: Boolean = false) = if (full) asFullTrace() else asShrinkTrace()

fun TraceEntity.asShrinkTrace() = Trace(
    id.value,
    LatLng(lat, lng)
)

fun TraceEntity.asFullTrace() = Trace(
    id.value,
    LatLng(lat, lng),
    stop?.asStop()
)