@file:UseSerializers(DateTimeTzSerializer::class)

package ru.tnsk.backend.domain.model.transport

import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import ru.tnsk.backend.core.serializers.DateTimeTzSerializer
import ru.tnsk.backend.domain.model.common.LatLng

@Serializable
data class Transport(
    val name: String, //  название маршрута
    val route: String, // внутреннее название маршрута
    val routeId: Int, // ID маршрута
    val transportType: TransportType, // тип транспорта
    val graph: Int,
    val direction: Direction, // направление
    val latLng: LatLng, // координаты
    val azimuth: Int, // азимут
    val timeNav: DateTimeTz, // время получения координаты
    val idTypetr: Int, // тип транспорта
    val rasp: String, // будущее расписание
    val speed: Double, // скорость
    val segmentOrder: Int, // текущий сегмент
    val ramp: Int
)