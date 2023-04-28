package ru.tnsk.backend.plugins

import io.ktor.server.application.*
import kotlinx.coroutines.launch
import org.koin.ktor.ext.get
import ru.tnsk.backend.service.TransportPositionService

fun Application.configureServices(
    transportPositionService: TransportPositionService = get()
) {
    launch(coroutineContext) {
        transportPositionService.start()
    }
}