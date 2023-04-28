package ru.tnsk.backend.sockets.transport

import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.serialization.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.repository.TransportMarkerRepository
import kotlin.time.Duration.Companion.minutes

fun Routing.transportSocket(transportMarkerRepository: TransportMarkerRepository = get()) {
    webSocket("/markers") { // todo коректировка по времени, синхронизация с обновлением БД
        while (true) {
            val markers = transportMarkerRepository.getAllMarkers(false)
            sendSerialized(MarkersResponse(markers))
            delay(1.minutes)
            println("transportSocket")
        }
    }

    webSocket("/markers/json") {
        while (true) {
            val markers = transportMarkerRepository.getAllMarkers(false)
            sendSerializedBase(
                MarkersResponse(markers),
                KotlinxWebsocketSerializationConverter(Json),
                call.request.headers.suitableCharset()
            )
            delay(1.minutes)
        }
    }
}