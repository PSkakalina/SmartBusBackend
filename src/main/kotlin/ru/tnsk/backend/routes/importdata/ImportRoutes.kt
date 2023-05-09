package ru.tnsk.backend.routes.importdata

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.repository.ImportRouteRepository
import ru.tnsk.backend.domain.model.account.UserRole
import ru.tnsk.backend.domain.repository.UserRepository
import ru.tnsk.backend.routes.utils.requireUser

fun Routing.importRoutes(
    importRouteRepository: ImportRouteRepository = get(),
    userRepository: UserRepository = get()
) {
    val route = "import"

    authenticate {
        get(route) {
            if (userRepository.getUserRole(requireUser().id) == UserRole.ADMIN) {
                importRouteRepository.upsertRoutes()

                call.respond(HttpStatusCode.NoContent)
            } else call.respond(HttpStatusCode.Unauthorized)
        }
    }
}