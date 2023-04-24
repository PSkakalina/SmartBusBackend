package ru.tnsk.backend.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TracesResponse(
    @SerialName("trasses")
    val routes: List<RouteTracesWrapper>
) {
    @Serializable
    data class RouteTracesWrapper(
        @SerialName("r")
        val routeTrace: List<RouteTraces>
    ) {
        @Serializable
        data class RouteTraces(
            val pc: String, // wtf?
            @SerialName("marsh")
            val route: String,
            @SerialName("u")
            val traces: List<Trace>
        ) {
            @Serializable
            data class Trace(
                val id: Int? = null,
                @SerialName("n")
                val name: String? = null,
                val len: Int? = null, // WTF?
                val lat: Double,
                val lng: Double,
            ) {
                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (other !is Trace) return false

                    if (id != other.id) return false
                    if (name != other.name) return false
                    if (lat != other.lat) return false
                    return lng == other.lng
                }

                override fun hashCode(): Int {
                    var result = id ?: 0
                    result = 31 * result + (name?.hashCode() ?: 0)
                    result = 31 * result + lat.hashCode()
                    result = 31 * result + lng.hashCode()
                    return result
                }
            }
        }
    }
}
