@file:UseSerializers(NskgtDateSerializer::class)

package ru.tnsk.backend.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import ru.tnsk.backend.core.serializers.NskgtDateSerializer
import ru.tnsk.backend.domain.model.transport.Direction
import java.util.*

@Serializable
data class MarkersResponse(val markers: List<Marker>) {

    @Serializable
    data class Marker(
        val title: String,
        @SerialName("marsh")
        val route: String,
        val graph: Int,
        val direction: Direction,
        val lat: Double,
        val lng: Double,
        @SerialName("time_nav")
        val timeNav: Date,
        @SerialName("id_typetr")
        val idTypetr: Int, //WTF
        val azimuth: Int,
        val rasp: String,
        val speed: Double,
        @SerialName("segment_order")
        val segmentOrder: Int,
        val ramp: Int,
    )
}