package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable

object RouteTraceTable : IntIdTable("route_to_trace") {
    val route = reference("route", RoutesTable)
    val trace = reference("trace", TracesTable)

    init {
//        uniqueIndex(route, trace)
    }
}