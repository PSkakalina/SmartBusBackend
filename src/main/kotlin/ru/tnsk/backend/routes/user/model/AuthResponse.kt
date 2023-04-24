package ru.tnsk.backend.routes.user.model

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.account.Token
import ru.tnsk.backend.domain.model.account.User

@Serializable
data class AuthResponse(
    val user: User,
    val token: Token
)
