package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.common.LatLng

@Serializable
data class Trace(
    val id: Int,
    val latLng: LatLng,
    val stop: Int? = null
)