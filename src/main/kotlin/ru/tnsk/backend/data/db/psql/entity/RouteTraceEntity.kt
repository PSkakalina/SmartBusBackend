package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.RouteTraceTable

class RouteTraceEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RouteTraceEntity>(RouteTraceTable)

    var route by RouteTraceTable.route
    var trace by RouteTraceTable.trace
}