package ru.tnsk.backend

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import ru.tnsk.backend.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSecurity()
    configureSerialization()
    configureDatabases()
    configureRouting()
    configureWebSockets()
    configureServices()

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

//    GlobalScope.launch {
//        val repo = get<TransportMarkerRepository>()
//
//        val m = repo.getAllMarkers()
//        println(m)
//    }
}
