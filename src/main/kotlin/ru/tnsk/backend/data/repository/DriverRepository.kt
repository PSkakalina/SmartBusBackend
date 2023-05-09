package ru.tnsk.backend.data.repository

import ru.tnsk.backend.data.db.psql.storage.DriverStorage
import ru.tnsk.backend.domain.model.account.Driver

class DriverRepository(
    private val driverStorage: DriverStorage
) {
    fun createDriver(
        userId: Int,
        routeId: Int? = null,
        notificationToken: String? = null
    ): Driver = driverStorage.create(userId, routeId, notificationToken)

    fun linkToRoute(id: Int, routeId: Int?): Driver? = driverStorage.setRouteId(id, routeId)

    fun setNotificationToken(id: Int, notificationToken: String?): Unit =
        driverStorage.setNotificationToken(id, notificationToken)

    fun findDriver(id: Int, fetchRoute: Boolean) = driverStorage.findDriver(id, fetchRoute)

    fun findDriverByUserId(userId: Int, fetchRoute: Boolean = false) =
        driverStorage.findDriverByUserId(userId, fetchRoute)
}