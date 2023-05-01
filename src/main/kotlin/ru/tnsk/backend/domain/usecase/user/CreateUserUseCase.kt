package ru.tnsk.backend.domain.usecase.user

import ru.tnsk.backend.core.useCase.ParamsUseCase
import ru.tnsk.backend.domain.model.account.User
import ru.tnsk.backend.domain.model.account.UserRole
import ru.tnsk.backend.domain.repository.UserRepository

class CreateUserUseCase(
    private val userRepository: UserRepository
) : ParamsUseCase<CreateUserUseCase.Input, User>() {
    override suspend fun run(params: Input): User {
        return with(params) {
            userRepository.createUser(login, name, passwordHash, role)
        }
    }

    data class Input(
        val login: String,
        val name: String,
        val passwordHash: String,
        val role: UserRole = UserRole.DEFAULT
    )
}