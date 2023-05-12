package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import ru.tnsk.backend.domain.model.transport.Direction
import ru.tnsk.backend.domain.model.transport.TransportType

object TransportHistoryTable : LongIdTable("transport_history") {
    val name = text("name")
    val routeName = text("routeName")
    val route = reference("route", RoutesTable.id)
    val transportType = enumeration<TransportType>("transportType")
    val graph = integer("graph")
    val direction = enumeration<Direction>("direction")
    val lat = double("lat")
    val lng = double("lng")
    val azimuth = integer("azimuth")
    val timeNav = datetime("timeNav")
    val timeZoneOffset = long("timeZoneOffset")
    val idTypetr = integer("idTypetr")
    val rasp = text("rasp")
    val speed = double("speed")
    val segmentOrder = integer("segmentOrder")
    val ramp = integer("ramp")
}