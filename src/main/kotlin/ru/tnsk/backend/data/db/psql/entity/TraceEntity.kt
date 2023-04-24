package ru.tnsk.backend.data.db.psql.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.tnsk.backend.data.db.psql.table.TracesTable

class TraceEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TraceEntity>(TracesTable)

    var lat by TracesTable.lat
    var lng by TracesTable.lng

    val stop by StopEntity optionalReferencedOn TracesTable.stop
    var stopId by TracesTable.stop

    val route by RouteEntity referencedOn TracesTable.route
    var routeId by TracesTable.route
}