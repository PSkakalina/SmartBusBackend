package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: Int, // уникальный идентификатор
    val route: String, // внутреннее название маршрута
    val name: String, // название маршрута
    val transportType: TransportType, // тип транспорта
    val firstStop: Stop, // первая остановка
    val lastStop: Stop // последняя остановка
)
