package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    val id: Int,
    val name: String,
    val len: Int,
    val trace: Trace?
)