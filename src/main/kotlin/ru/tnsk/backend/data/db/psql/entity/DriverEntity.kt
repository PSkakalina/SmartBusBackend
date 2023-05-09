package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.DriversTable

class DriverEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DriverEntity>(DriversTable)

    var userId by DriversTable.userId
    var routeId by DriversTable.routeId
    var notificationToken by DriversTable.notificationToken

    val user by UserEntity referencedOn DriversTable.userId
    val route by RouteEntity optionalReferencedOn DriversTable.routeId
}