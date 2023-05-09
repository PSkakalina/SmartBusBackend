package ru.tnsk.backend.routes.driver.model

import kotlinx.serialization.Serializable

@Serializable
data class ChangeTokenRequest(val token: String)