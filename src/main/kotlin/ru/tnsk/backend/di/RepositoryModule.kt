package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.repository.ImportRouteRepository
import ru.tnsk.backend.data.repository.RouteRepository
import ru.tnsk.backend.data.repository.TransportMarkerRepository
import ru.tnsk.backend.data.repository.UserRepositoryImpl
import ru.tnsk.backend.domain.repository.UserRepository

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<ImportRouteRepository> { ImportRouteRepository(get(), get(), get(), get()) }
    factory<RouteRepository> { RouteRepository(get()) }
    single<TransportMarkerRepository> { TransportMarkerRepository(get(), get()) }
}