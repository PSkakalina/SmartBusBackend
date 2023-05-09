package ru.tnsk.backend.routes.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import org.mindrot.jbcrypt.BCrypt
import ru.tnsk.backend.data.repository.DriverRepository
import ru.tnsk.backend.domain.model.account.Token
import ru.tnsk.backend.domain.model.account.UserRole
import ru.tnsk.backend.domain.usecase.user.AuthUserUseCase
import ru.tnsk.backend.domain.usecase.user.CreateUserUseCase
import ru.tnsk.backend.domain.usecase.user.GetFullUserUseCase
import ru.tnsk.backend.plugins.JwtConfig
import ru.tnsk.backend.routes.user.model.AuthResponse
import ru.tnsk.backend.routes.user.model.CreateUserRequest
import ru.tnsk.backend.routes.user.model.DriverAuthResponse
import ru.tnsk.backend.routes.user.model.LoginRequest

fun Routing.accountRoutes(
    createUserUseCase: CreateUserUseCase = get(),
    authUserUseCase: AuthUserUseCase = get(),
    getFullUserUseCase: GetFullUserUseCase = get(),
    driverRepository: DriverRepository = get()
) {
    val route = "account"

    post<CreateUserRequest>("$route/register") {
        with(it) {
            createUserUseCase.execute(
                CreateUserUseCase.Input(
                    login,
                    name,
                    BCrypt.hashpw(password, BCrypt.gensalt()),
                )
            )
        }.let { user ->
            call.respond(
                AuthResponse(
                    user,
                    Token(
                        JwtConfig.generateToken(user),
                        "Bearer"
                    )
                )
            )
        }
    }

    // todo дополнительные проверки что бы не каждый мог зарегаться как водила
    post<CreateUserRequest>("$route/register/driver") {
        with(it) {
            createUserUseCase.execute(
                CreateUserUseCase.Input(
                    login,
                    name,
                    BCrypt.hashpw(password, BCrypt.gensalt()),
                    UserRole.DRIVER
                )
            )
        }.let { user ->
            call.respond(
                AuthResponse(
                    user,
                    Token(
                        JwtConfig.generateToken(user),
                        "Bearer"
                    )
                )
            )
        }
    }

    // todo дополнительные проверки что бы не каждый мог зарегаться как водила
    post<CreateUserRequest>("$route/driver/register") {
        with(it) {
            createUserUseCase.execute(
                CreateUserUseCase.Input(
                    login,
                    name,
                    BCrypt.hashpw(password, BCrypt.gensalt()),
                    UserRole.DRIVER
                )
            )
        }.let { user ->
            driverRepository.findDriverByUserId(user.id, true)!!
        }.let { driver ->
            call.respond(
                DriverAuthResponse(
                    driver,
                    Token(
                        JwtConfig.generateToken(driver.userInfo),
                        "Bearer"
                    )
                )
            )
        }
    }

    post<LoginRequest>("$route/login") {
        authUserUseCase.execute(it)?.let { (user, token) ->
            call.respond(
                AuthResponse(
                    user,
                    token
                )
            )
        } ?: call.respond(HttpStatusCode.NotFound)
    }

    post<LoginRequest>("$route/driver/login") {
        val userRole = getFullUserUseCase.execute(
            GetFullUserUseCase.Input.Login(it.login)
        )?.role ?: call.respond(HttpStatusCode.NotFound)

        if (userRole == UserRole.DRIVER || userRole == UserRole.ADMIN)
            authUserUseCase.execute(it)
                ?.let { (user, token) ->
                    driverRepository.findDriverByUserId(user.id, true)?.let { it to token }
                }
                ?.let { (driver, token) ->
                    call.respond(
                        DriverAuthResponse(
                            driver,
                            token
                        )
                    )
                } ?: call.respond(HttpStatusCode.NotFound)

        call.respond(HttpStatusCode.Unauthorized)
    }
}