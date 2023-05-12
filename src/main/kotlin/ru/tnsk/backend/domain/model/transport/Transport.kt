@file:UseSerializers(DateTimeTzSerializer::class)

package ru.tnsk.backend.domain.model.transport

import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import ru.tnsk.backend.core.serializers.DateTimeTzSerializer
import ru.tnsk.backend.domain.model.common.LatLng

@Serializable
data class Transport(
    val name: String,
    val route: String,
    val routeId: Int,
    val transportType: TransportType,
    val graph: Int,
    val direction: Direction,
    val latLng: LatLng,
    val azimuth: Int,
    val timeNav: DateTimeTz,
    val idTypetr: Int, //WTF
    val rasp: String,
    val speed: Double,
    val segmentOrder: Int,
    val ramp: Int
)