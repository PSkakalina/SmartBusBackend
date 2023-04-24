package ru.tnsk.backend.data.db.psql.storage.stop

import ru.tnsk.backend.data.db.psql.entity.StopEntity
import ru.tnsk.backend.data.db.psql.storage.trace.asTrace
import ru.tnsk.backend.domain.model.transport.Stop

fun StopEntity.asStop() = Stop(
    id.value,
    name,
    len,
    trace?.asTrace()
)