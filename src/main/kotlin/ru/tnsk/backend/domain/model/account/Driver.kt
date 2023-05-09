package ru.tnsk.backend.domain.model.account

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.transport.Route

@Serializable
class Driver(
    val id: Int,
    val userInfo: User,
    val notificationToken: String?,
    val route: Route? = null
)