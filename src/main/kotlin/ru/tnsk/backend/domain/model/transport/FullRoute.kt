package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable

@Serializable
data class FullRoute(
    val route: Route,
    val traces: List<Trace>
)