package ru.tnsk.backend.domain.usecase.user

import org.mindrot.jbcrypt.BCrypt
import ru.tnsk.backend.core.useCase.ParamsUseCase
import ru.tnsk.backend.domain.model.account.Token
import ru.tnsk.backend.domain.model.account.User
import ru.tnsk.backend.domain.repository.UserRepository
import ru.tnsk.backend.plugins.JwtConfig
import ru.tnsk.backend.routes.user.model.LoginRequest

class AuthUserUseCase(
    private val userRepository: UserRepository
) : ParamsUseCase<LoginRequest, Pair<User, Token>?>() {
    override suspend fun run(params: LoginRequest): Pair<User, Token>? {
        return userRepository.getFullUser(params.login)?.let { user ->
            val token = if (BCrypt.checkpw(params.password, user.passwordHash))
                JwtConfig.generateToken(user.asUser())
            else null

            token?.let { user.asUser() to Token(it) }
        }
    }
}