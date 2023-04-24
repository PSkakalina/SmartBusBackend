package ru.tnsk.backend.routes.route.model

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.transport.Route

@Serializable
data class AllRoutesResponse(
    private val routes: List<Route>
)
