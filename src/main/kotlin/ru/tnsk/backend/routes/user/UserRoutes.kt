package ru.tnsk.backend.routes.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get
import org.mindrot.jbcrypt.BCrypt
import ru.tnsk.backend.domain.model.account.Token
import ru.tnsk.backend.domain.usecase.user.AuthUserUseCase
import ru.tnsk.backend.domain.usecase.user.CreateUserUseCase
import ru.tnsk.backend.domain.usecase.user.GetUserUseCase
import ru.tnsk.backend.plugins.JwtConfig
import ru.tnsk.backend.routes.user.model.AuthResponse
import ru.tnsk.backend.routes.user.model.CreateUserRequest
import ru.tnsk.backend.routes.user.model.LoginRequest

fun Routing.accountRoutes(
    createUserUseCase: CreateUserUseCase = get(),
    authUserUseCase: AuthUserUseCase = get(),
    getUserUseCase: GetUserUseCase = get()
) {
    val route = "user"

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

    get("$route/{login}") {
        val login = call.parameters["login"]!!
        val id = login.toIntOrNull()

        val user = if (id == null) {
            getUserUseCase.execute(GetUserUseCase.Input.Login(login))
        } else getUserUseCase.execute(GetUserUseCase.Input.Id(id))

        user?.let {
            call.respond(
                it
            )
        } ?: call.respond(HttpStatusCode.NotFound)
    }
}