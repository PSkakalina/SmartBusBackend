package ru.tnsk.backend.routes.transport

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.db.psql.storage.TransportHistoryStorage

fun Routing.transportRoutes(
    transportHistoryStorage: TransportHistoryStorage = get()
) {
    val route = "transport"
    get(route) {
        call.respond(transportHistoryStorage.getAll())
    }

    get("$route/pb") {
        call.respond(transportHistoryStorage.getAll())
    }
}