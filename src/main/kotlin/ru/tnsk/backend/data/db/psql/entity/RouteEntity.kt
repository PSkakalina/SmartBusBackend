package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.TracesTable

class RouteEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RouteEntity>(RoutesTable)

    var route by RoutesTable.route
    var name by RoutesTable.name
    var transportType by RoutesTable.transportType
    val firstStop by StopEntity referencedOn RoutesTable.firstStop
    var firstStopId by RoutesTable.firstStop

    val lastStop by StopEntity referencedOn RoutesTable.lastStop
    var lastStopId by RoutesTable.lastStop

    val traces by TraceEntity referrersOn TracesTable.route
}