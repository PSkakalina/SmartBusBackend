package ru.tnsk.backend.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.util.pipeline.*
import ru.tnsk.backend.domain.model.account.User

fun PipelineContext<Unit, ApplicationCall>.requireUser(): User = user ?: error("No user in authentication")
val PipelineContext<Unit, ApplicationCall>.user: User?
    get() = call.authentication.principal()