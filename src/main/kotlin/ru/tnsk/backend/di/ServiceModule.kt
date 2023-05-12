package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.service.TransportPositionService

val serviceModule = module {
    single { TransportPositionService(get(), get()) }
}