package ru.tnsk.backend.routes.transport

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.repository.TransportHistoryRepository

fun Routing.transportRoutes(
    transportHistoryStorage: TransportHistoryRepository = get()
) {
    val route = "transport"
    get(route) {
        call.respond(transportHistoryStorage.all(10000))
    }

    get("$route/pb") {
        call.respond(ProtoBuf.encodeToByteArray(transportHistoryStorage.all(10000)))
    }

    get("$route/{routeId}") {
        val routeId = call.parameters["routeId"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond(ProtoBuf.encodeToByteArray(transportHistoryStorage.find(routeId)))
    }

    get("$route/{routeId}/j") {
        val routeId = call.parameters["routeId"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond(transportHistoryStorage.find(routeId))
    }
}