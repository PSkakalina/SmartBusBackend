package ru.tnsk.backend.di

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import ru.tnsk.backend.data.db.psql.TnskDatabase

fun dbModule(application: Application) = module {
    single { TnskDatabase.connect(application.jdbcUrl) }
    factory<Database> { get<TnskDatabase>().db }
}

private val Application.jdbcUrl: String
    get() = environment.config.property("ktor.jdbcDatabaseUrl").getString()