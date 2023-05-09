package ru.tnsk.backend.data.db.mappers

import ru.tnsk.backend.data.db.psql.entity.DriverEntity
import ru.tnsk.backend.domain.model.account.Driver

fun DriverEntity.asDriver(fetchRoute: Boolean) = Driver(
    id.value,
    user.asUser(),
    notificationToken,
    if (fetchRoute) route?.asRoute() else null
)