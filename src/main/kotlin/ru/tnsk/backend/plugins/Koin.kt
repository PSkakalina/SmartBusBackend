package ru.tnsk.backend.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import ru.tnsk.backend.di.*

fun Application.configureKoin() {
    install(Koin) {
        modules(
            networkModule,
            dbModule(this@configureKoin),
            storageModule,
            useCaseModule,
            repositoryModule,
            commonModule
        )
    }
}