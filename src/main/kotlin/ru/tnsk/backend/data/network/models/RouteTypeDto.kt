@file:UseSerializers(NskgtTransportTypeSerializer::class)

package ru.tnsk.backend.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import ru.tnsk.backend.core.serializers.NskgtTransportTypeSerializer
import ru.tnsk.backend.domain.model.transport.TransportType

@Serializable
data class RouteTypeDto(
    val type: TransportType,
    val ways: List<Route> // route
) {
    @Serializable
    data class Route(
        @SerialName("marsh")
        val route: String,
        val name: String,
        val stopb: String,
        val stope: String
    )
}