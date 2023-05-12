package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.TransportHistoryTable

class TransportHistoryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransportHistoryEntity>(TransportHistoryTable)

    var name by TransportHistoryTable.name
    var routeName by TransportHistoryTable.routeName
    var routeId by TransportHistoryTable.route
    var transportType by TransportHistoryTable.transportType
    var graph by TransportHistoryTable.graph
    var direction by TransportHistoryTable.direction
    var lat by TransportHistoryTable.lat
    var lng by TransportHistoryTable.lng
    var azimuth by TransportHistoryTable.azimuth
    var timeNav by TransportHistoryTable.timeNav
    var timeZoneOffset by TransportHistoryTable.timeZoneOffset
    var idTypetr by TransportHistoryTable.idTypetr
    var rasp by TransportHistoryTable.rasp
    var speed by TransportHistoryTable.speed
    var segmentOrder by TransportHistoryTable.segmentOrder
    var ramp by TransportHistoryTable.ramp

    val route by RouteEntity referencedOn TransportHistoryTable.route
}