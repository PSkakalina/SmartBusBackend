package ru.tnsk.backend.domain.model.transport

data class FullRoute(
    val route: Route,
    val traces: List<Trace>
)