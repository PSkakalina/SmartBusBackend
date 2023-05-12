package ru.tnsk.backend.routes.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import ru.tnsk.backend.domain.model.account.User

suspend fun PipelineContext<Unit, ApplicationCall>.requireUser(): User = user ?: run {
    unauthorized()
    error("No user in authentication")
}

val PipelineContext<Unit, ApplicationCall>.user: User?
    get() = call.authentication.principal()

suspend fun PipelineContext<*, ApplicationCall>.unauthorized() {
    call.respond(HttpStatusCode.Unauthorized)
}

suspend fun PipelineContext<*, ApplicationCall>.badRequest(error: String = "") {
    call.respond(HttpStatusCode.BadRequest, error)
}