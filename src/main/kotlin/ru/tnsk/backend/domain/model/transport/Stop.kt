package ru.tnsk.backend.domain.model.transport

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.common.LatLng

@Serializable
data class Stop(
    val id: Int, // Уникальный идентификатор
    val name: String, // Название
    val latLng: LatLng, // Координаты
    val len: Int
)