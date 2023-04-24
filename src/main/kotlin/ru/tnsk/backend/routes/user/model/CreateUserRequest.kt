package ru.tnsk.backend.routes.user.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val login: String,
    val name: String,
    val password: String
)