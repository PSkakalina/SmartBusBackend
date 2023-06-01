package ru.tnsk.backend.data.repository

import com.github.benmanes.caffeine.cache.Caffeine
import ru.tnsk.backend.data.db.psql.storage.RouteStorage
import ru.tnsk.backend.domain.model.transport.FullRoute
import ru.tnsk.backend.domain.model.transport.Route
import ru.tnsk.backend.domain.model.transport.TransportType

/**
 * Репозиторий маршрутов
 */
class RouteRepository(
    private val routeStorage: RouteStorage
) {
    /**
     * Кэш для запросов по получению маршрутов по id
     */
    private val findRouteCache = Caffeine.newBuilder()
        .maximumSize(140)
        .build<Int, Route> { routeStorage.findRoute(it) }

    /**
     * Кэш для получения всех маршутов
     */
    private val allRoutesCache = Caffeine.newBuilder()
        .maximumSize(1)
        .build<Unit, List<Route>> { routeStorage.getAllRoutes() }

    /**
     * Кэш для получения полного маршрута по id
     */
    private val findFullRoutesCache = Caffeine.newBuilder()
        .maximumSize(140)
        .build<Int, FullRoute> { routeStorage.findFullRoute(it, true) }

    /**
     * Запрос всех маршрутов
     */
    fun getRoutes(): List<Route> {
        return allRoutesCache.get(Unit)
    }

    /**
     * Поиск маршрута по id
     */
    fun findRoute(id: Int): Route? = findRouteCache.get(id)

    /**
     * Поиск полного маршрута по id
     */
    fun findFullRoute(id: Int): FullRoute? = findFullRoutesCache.get(id)

    /**
     * поиск маршрута по внутреннему названию и типу транспорта
     */
    fun findRoute(internalRouteName: String, transportType: TransportType): Route? =
        routeStorage.findRoute(transportType, internalRouteName)

    /**
     * поиск маршрута по названию и типу транспорта
     */
    fun findRoutes(routeName: String, transportType: TransportType? = null): List<Route> {
        return transportType?.let {
            routeStorage.findRoutes(routeName, it)
        } ?: routeStorage.findRoutes(routeName)
    }
}