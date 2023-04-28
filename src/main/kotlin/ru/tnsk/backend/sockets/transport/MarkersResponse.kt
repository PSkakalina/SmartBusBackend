package ru.tnsk.backend.sockets.transport

import kotlinx.serialization.Serializable
import ru.tnsk.backend.domain.model.transport.Transport

@Serializable
data class MarkersResponse(val markers: List<Transport>)