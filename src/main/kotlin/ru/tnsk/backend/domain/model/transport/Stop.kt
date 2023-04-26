package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.common.LatLng

@Serializable
data class Stop(
    val id: Int,
    val name: String,
    val latLng: LatLng,
    val len: Int
)