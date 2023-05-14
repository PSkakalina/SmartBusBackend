package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.repository.*
import ru.tnsk.backend.domain.repository.UserRepository

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<ImportRouteRepository> { ImportRouteRepository(get(), get(), get(), get()) }
    factory<RouteRepository> { RouteRepository(get()) }
    factory<DriverRepository> { DriverRepository(get()) }
    single<TransportMarkerRepository> { TransportMarkerRepository(get(), get()) }
    single<TransportHistoryRepository> { TransportHistoryRepository(get()) }
}