package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.domain.usecase.user.AuthUserUseCase
import ru.tnsk.backend.domain.usecase.user.CreateUserUseCase
import ru.tnsk.backend.domain.usecase.user.GetUserUseCase

val useCaseModule = module {
    factory { GetUserUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { AuthUserUseCase(get()) }
}