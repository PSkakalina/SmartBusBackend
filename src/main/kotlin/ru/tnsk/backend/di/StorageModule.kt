package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.db.psql.storage.RouteStorage
import ru.tnsk.backend.data.db.psql.storage.StopStorage
import ru.tnsk.backend.data.db.psql.storage.TraceStorage
import ru.tnsk.backend.data.db.psql.storage.UserStorage
import ru.tnsk.backend.data.network.storage.NskgtStorage

val storageModule = module {
    factory { UserStorage(get()) }
    factory { RouteStorage(get()) }
    factory { TraceStorage(get()) }
    factory { StopStorage(get()) }
    factory { NskgtStorage(get()) }
}


