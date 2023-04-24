package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: Int,
    val route: String,
    val name: String,
    val transportType: TransportType,
    val firstStop: Stop,
    val lastStop: Stop
)
