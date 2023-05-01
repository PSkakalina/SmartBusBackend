package ru.tnsk.backend.domain.usecase.user

import ru.tnsk.backend.core.useCase.ParamsUseCase
import ru.tnsk.backend.domain.model.account.FullUser
import ru.tnsk.backend.domain.repository.UserRepository

class GetFullUserUseCase(
    private val userRepository: UserRepository
) : ParamsUseCase<GetFullUserUseCase.Input, FullUser?>() {
    override suspend fun run(params: Input): FullUser? {
        return when (params) {
            is Input.Id -> userRepository.getFullUser(params.id)
            is Input.Login -> userRepository.getFullUser(params.login)
        }
    }

    sealed interface Input {
        data class Login(val login: String) : Input
        data class Id(val id: Int) : Input
    }
}