package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.mappers.asTrace
import ru.tnsk.backend.data.db.psql.entity.RouteEntity
import ru.tnsk.backend.data.db.psql.entity.RouteTraceEntity
import ru.tnsk.backend.data.db.psql.entity.TraceEntity
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.StopsTable
import ru.tnsk.backend.data.db.psql.table.TracesTable
import ru.tnsk.backend.domain.model.transport.Trace

class TraceStorage(
    private val db: Database
) {
    fun create(lat: Double, lng: Double, stopId: Int?, routeId: Int) = transaction(db) {
        val newTrace = TraceEntity.new {
            this.lat = lat
            this.lng = lng
            this.stopId = stopId?.let { EntityID(it, StopsTable) }
        }

        linkToRoute(newTrace.id, routeId)

        newTrace.asTrace()
    }

    fun createOrGet(lat: Double, lng: Double, stopId: Int?, routeId: Int): Trace = transaction(db) {
        val entity = TraceEntity.find {
            TracesTable.lat eq lat and (TracesTable.lng eq lng) and (TracesTable.stop eq stopId)
        }.firstOrNull()

        entity?.let {
            val alreadyLinked = RouteEntity.findById(routeId)?.traces?.contains(entity) ?: false
//        if (!alreadyLinked) linkToRoute(entity.id, routeId)
            linkToRoute(it.id, routeId)

            it.asTrace()
        } ?: create(lat, lng, stopId, routeId)
    }

    private fun linkToRoute(traceId: EntityID<Int>, routeId: Int) {
        RouteTraceEntity.new {
            route = EntityID(routeId, RoutesTable)
            trace = traceId
        }
    }
}