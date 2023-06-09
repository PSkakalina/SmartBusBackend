package ru.tnsk.backend.data.db.psql.storage

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tnsk.backend.data.db.mappers.asFullRoute
import ru.tnsk.backend.data.db.mappers.asRoute
import ru.tnsk.backend.data.db.psql.entity.RouteEntity
import ru.tnsk.backend.data.db.psql.table.RoutesTable
import ru.tnsk.backend.data.db.psql.table.StopsTable
import ru.tnsk.backend.domain.model.transport.Route
import ru.tnsk.backend.domain.model.transport.TransportType

/**
 * Класс содержащий в себе логику работы с БД связанную с маршрутами
 */
class RouteStorage(
    private val db: Database
) {
    /**
     * Создание маршрута
     */
    fun create(
        marsh: String, name: String, transportType: TransportType, firstStopId: Int, lastStopId: Int
    ) = transaction(db) {
        RouteEntity.new {
            this.route = marsh
            this.name = name
            this.transportType = transportType
            this.firstStopId = EntityID(firstStopId, StopsTable)
            this.lastStopId = EntityID(lastStopId, StopsTable)
        }
    }

    /**
     * Получение всех маршрутов
     */
    fun getAllRoutes(): List<Route> = transaction(db) {
        RouteEntity.all().map { it.asRoute() }
    }

    /**
     * Поиск маршрута по id
     */
    fun findRoute(id: Int) = transaction(db) { RouteEntity.findById(id)?.asRoute() }

    /**
     * Поиск "полного" маршрута по id
     * @return маршрут с точками и остановками
     */
    fun findFullRoute(id: Int, withStops: Boolean) =
        transaction(db) { RouteEntity.findById(id)?.asFullRoute(withStops) }

    /**
     * поиск маршрута по типу транспорта и внутреннему названию
     */
    fun findRoute(transportType: TransportType, route: String) = transaction(db) {
        findRoutes(route, transportType).firstOrNull()
    }

    /**
     * Поиск маршрутов по названию и типу траспорта
     */
    fun findRoutes(name: String, transportType: TransportType) = transaction(db) {
        RouteEntity.find {
            RoutesTable.transportType eq transportType and (RoutesTable.name.lowerCase() like "%${name.lowercase()}%")
        }.map { it.asRoute() }
    }

    /**
     * Поиск маршрутов по названию
     */
    fun findRoutes(name: String) = transaction(db) {
        RouteEntity.find {
            RoutesTable.name.lowerCase() like "%${name.lowercase()}%"
        }.map { it.asRoute() }
    }
}