package ru.tnsk.backend.domain.model.transport

enum class TransportType(val value: Int) {
    BUS(0), // Автобус
    TROLLEY_BUS(1), // Троллейбус
    TRAM(2), // Трамвай
    MINIBUS(7) // Маршрутка
}