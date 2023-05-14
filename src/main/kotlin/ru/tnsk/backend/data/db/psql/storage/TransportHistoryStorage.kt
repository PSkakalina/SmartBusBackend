package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.core.utils.asLocalDateTime
import ru.tnsk.backend.core.utils.zoneOffset
import ru.tnsk.backend.data.db.mappers.asTransport
import ru.tnsk.backend.data.db.psql.entity.TransportHistoryEntity
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.TransportHistoryTable
import ru.tnsk.backend.domain.model.transport.Transport


class TransportHistoryStorage(
    private val db: Database
) {
    fun create(
        transport: Transport
    ) = transaction(db) {
        TransportHistoryEntity.new {
            name = transport.name
            routeName = transport.route
            routeId = EntityID(transport.routeId, RoutesTable)
            transportType = transport.transportType
            graph = transport.graph
            direction = transport.direction
            lat = transport.latLng.lat
            lng = transport.latLng.lng
            azimuth = transport.azimuth
            timeNav = transport.timeNav.asLocalDateTime()
            timeZoneOffset = transport.timeNav.zoneOffset
            idTypetr = transport.idTypetr
            rasp = transport.rasp
            speed = transport.speed
            segmentOrder = transport.segmentOrder
            ramp = transport.ramp
        }.asTransport()
    }

    fun getAll(limit: Int) = transaction(db) {
        TransportHistoryEntity.all().limit(limit).map { it.asTransport() }
    }

    fun find(routeId: Int) = transaction(db) {
        TransportHistoryEntity.find { TransportHistoryTable.route eq routeId }.map { it.asTransport() }.toList()
    }
}