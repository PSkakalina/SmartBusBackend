package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable

object DriversTable : IntIdTable("drivers") {
    val userId = reference("userId", UsersTable.id).uniqueIndex()
    val routeId = reference("routeId", RoutesTable.id).nullable()
    val notificationToken = text("notificationToken").nullable()
}