package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import ru.tnsk.backend.data.db.mappers.asDriver
import ru.tnsk.backend.data.db.psql.entity.DriverEntity
import ru.tnsk.backend.data.db.psql.table.DriversTable
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.UsersTable
import ru.tnsk.backend.domain.model.account.Driver

/**
 * Хранилище данных водителей
 */
class DriverStorage(
    private val db: Database
) {
    fun create(
        userId: Int,
        routeId: Int? = null,
        notificationToken: String? = null,
    ): Driver = transaction(db) {
        DriverEntity.new {
            this.userId = EntityID(userId, UsersTable)
            routeId?.let {
                this.routeId = EntityID(it, RoutesTable)
            }
            this.notificationToken = notificationToken
        }.asDriver(true)
    }

    fun setNotificationToken(
        id: Int,
        notificationToken: String?
    ): Unit = transaction(db) {
        DriversTable.update({ DriversTable.id eq id }) {
            it[DriversTable.notificationToken] = notificationToken
        }
    }

    fun setRouteId(
        id: Int,
        routeId: Int?
    ): Driver? = transaction(db) {
        DriversTable.update({ DriversTable.id eq id }) {
            it[DriversTable.routeId] = routeId?.let { rId -> EntityID(rId, RoutesTable) }
        }

        findDriver(id, true)
    }

    fun findDriver(id: Int, fetchRoute: Boolean): Driver? = transaction(db) {
        DriverEntity.findById(id)?.asDriver(fetchRoute)
    }

    fun findDriverByUserId(userId: Int, fetchRoute: Boolean): Driver? = transaction(db) {
        DriverEntity.find { DriversTable.userId eq userId }.firstOrNull()?.asDriver(fetchRoute)
    }
}