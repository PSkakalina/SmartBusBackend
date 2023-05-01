package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.domain.usecase.user.AuthUserUseCase
import ru.tnsk.backend.domain.usecase.user.CreateUserUseCase
import ru.tnsk.backend.domain.usecase.user.GetFullUserUseCase

val useCaseModule = module {
    factory { GetFullUserUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { AuthUserUseCase(get()) }
}