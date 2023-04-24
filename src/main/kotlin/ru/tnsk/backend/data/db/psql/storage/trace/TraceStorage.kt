package ru.tnsk.backend.data.db.psql.storage.trace

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.psql.entity.TraceEntity
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.StopsTable

class TraceStorage(
    private val db: Database
) {
    fun create(
        lat: Double,
        lng: Double,
        stopId: Int?,
        routeId: Int
    ) = transaction(db) {
        TraceEntity.new {
            this.lat = lat
            this.lng = lng
            this.stopId = stopId?.let { EntityID(it, StopsTable) }
            this.routeId = EntityID(routeId, RoutesTable)
        }.asTrace()
    }
}