package ru.tnsk.backend.domain.usecase

import ru.tnsk.backend.data.repository.RouteRepository
import ru.tnsk.backend.domain.model.transport.Route
import ru.tnsk.backend.domain.model.transport.TransportType

class SearchQueryProcessor(
    private val routeRepository: RouteRepository
) {

    private val queryByTransportType = mapOf(
        "автобус" to TransportType.BUS,
        "троллейбус" to TransportType.TROLLEY_BUS,
        "трамвай" to TransportType.TRAM,
        "маршрутка" to TransportType.MINIBUS
    )

    private val englishByRussianChars = mapOf(
        'a' to 'а',
        'b' to 'в',
        'c' to 'с',
        'e' to 'е',
        'h' to 'н',
        'k' to 'к',
        'm' to 'м',
        'o' to 'о',
        'p' to 'р',
        't' to 'т',
        'y' to 'у',
        'u' to 'и',
    )

    fun process(query: String, type: Int? = null): List<Route> {
        val splitQuery = query.trim().split(" ")

        val transportType = TransportType.values().find { it.value == type }

        if (transportType != null && splitQuery.size == 1) {
            return routeRepository.findRoutes(splitQuery.first().fixLetters(), transportType)
        }

        return when (splitQuery.size) {
            1 -> {
                routeRepository.findRoutes(splitQuery.first().fixLetters())
            }

            2 -> {
                val stringType = splitQuery.find { queryByTransportType[it.lowercase()] != null } ?: return emptyList()
                val typePosition = splitQuery.indexOf(stringType)

                val routeName = splitQuery[getRoutePosition(typePosition)]
                val queryType = stringType.let { queryByTransportType[it.lowercase()] }

                routeRepository.findRoutes(routeName.fixLetters(), queryType)

            }

            else -> emptyList()
        }
    }

    private fun getRoutePosition(typePosition: Int): Int = when (typePosition) {
        0 -> 1
        else -> 0
    }

    private fun String.fixLetters(): String {
        return buildString {
            this@fixLetters.forEach {
                if (englishByRussianChars[it] == null) {
                    append(it)
                } else {
                    append(englishByRussianChars[it])
                }
            }
        }
    }
}