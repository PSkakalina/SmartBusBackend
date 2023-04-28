@file:UseSerializers(NskgtDateSerializer::class)

package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import ru.tnsk.backend.core.serializers.NskgtDateSerializer
import ru.tnsk.backend.domain.model.common.LatLng
import java.util.*

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
    val timeNav: Date,
    val idTypetr: Int, //WTF
    val rasp: String,
    val speed: Double,
    val segmentOrder: Int,
    val ramp: Int
)