package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable

object TracesTable : IntIdTable("traces") {
    val lat = double("lat")
    val lng = double("lng")
    val stop = reference("stop", StopsTable).nullable()
    val route = reference("route", RoutesTable)
}