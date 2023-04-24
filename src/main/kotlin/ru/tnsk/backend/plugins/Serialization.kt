package ru.tnsk.backend.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.get

fun Application.configureSerialization(json: Json = get()) {
    install(ContentNegotiation) {
        json(json)
    }
}
