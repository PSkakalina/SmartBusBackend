package ru.tnsk.backend.domain.model.account

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String,
    val type: String = "Bearer"
)