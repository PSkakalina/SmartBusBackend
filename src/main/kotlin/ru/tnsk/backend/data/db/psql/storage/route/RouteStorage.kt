package ru.tnsk.backend.data.db.psql.storage.route

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.psql.entity.RouteEntity
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.StopsTable
import ru.tnsk.backend.domain.model.transport.Route
import ru.tnsk.backend.domain.model.transport.TransportType

class RouteStorage(
    private val db: Database
) {
    fun create(
        marsh: String, name: String, transportType: TransportType, firstStopId: Int, lastStopId: Int
    ): Route = transaction(db) {
        RouteEntity.new {
            this.route = marsh
            this.name = name
            this.transportType = transportType
            this.firstStopId = EntityID(firstStopId, StopsTable)
            this.lastStopId = EntityID(lastStopId, StopsTable)
        }.asRoute()
    }

    fun getAllRoutes() = transaction(db) {
        RouteEntity.all().map { it.asRoute() }
    }

    fun findRoute(id: Int) = transaction(db) { RouteEntity.findById(id)?.asRoute() }

    fun findRoute(transportType: TransportType, route: String) = transaction(db) {
        RouteEntity.find {
            RoutesTable.transportType eq transportType and (RoutesTable.route eq route)
        }.firstOrNull()?.asRoute()
    }
}