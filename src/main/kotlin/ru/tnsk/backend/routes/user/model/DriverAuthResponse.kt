package ru.tnsk.backend.routes.user.model

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.account.Driver
import ru.tnsk.backend.domain.model.account.Token

@Serializable
data class DriverAuthResponse(
    val diver: Driver,
    val token: Token
)
