package ru.tnsk.backend.routes.driver

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.repository.DriverRepository
import ru.tnsk.backend.domain.repository.UserRepository
import ru.tnsk.backend.routes.driver.model.ChangeRouteRequest
import ru.tnsk.backend.routes.driver.model.ChangeTokenRequest
import ru.tnsk.backend.routes.utils.requireUser
import ru.tnsk.backend.routes.utils.unauthorized

fun Routing.driverRoutes(
    userRepository: UserRepository = get(),
    driverRepository: DriverRepository = get()
) {
    val route = "driver"

    authenticate {
        post<ChangeTokenRequest>("$route/notification") {
            val user = requireUser()

            driverRepository.findDriverByUserId(userId = user.id, false)?.let { driver ->
                driverRepository.setNotificationToken(driver.id, it.token)

                call.respond(HttpStatusCode.NoContent)
            }

            unauthorized()
        }

        post<ChangeRouteRequest>("$route/route") {
            val user = requireUser()

            driverRepository.findDriverByUserId(userId = user.id, false)?.let { driver ->
                driverRepository.linkToRoute(driver.id, it.routeId)

                call.respond(HttpStatusCode.NoContent)
            }

            unauthorized()
        }
    }
}