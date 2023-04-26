package ru.tnsk.backend

import io.ktor.server.application.*
import io.ktor.server.netty.*
import ru.tnsk.backend.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSecurity()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
