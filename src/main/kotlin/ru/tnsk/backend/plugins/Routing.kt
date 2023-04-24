package ru.tnsk.backend.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.tnsk.backend.routes.route.routeRoutes
import ru.tnsk.backend.routes.user.accountRoutes

fun Application.configureRouting() {
    install(Resources)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        openAPI(path = "openapi", swaggerFile = "openapi/documentation.yaml")

        accountRoutes()
        routeRoutes()
    }
}