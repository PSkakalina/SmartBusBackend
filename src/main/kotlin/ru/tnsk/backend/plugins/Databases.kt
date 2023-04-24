package ru.tnsk.backend.plugins

import io.ktor.server.application.*
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.db.psql.TnskDatabase

fun Application.configureDatabases(tnskDatabase: TnskDatabase = get()) {
    tnskDatabase.createTablesIfNeeded()
}
