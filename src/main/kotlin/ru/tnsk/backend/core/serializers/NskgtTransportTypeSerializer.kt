package ru.tnsk.backend.core.serializers

import ru.tnsk.backend.domain.model.transport.TransportType

object NskgtTransportTypeSerializer : EnumAsIntSerializer<TransportType>(
    "TransportType",
    { it.value },
    { value -> TransportType.values().find { it.value == value } ?: error("Нет такого элемента: $value") }
)