package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.db.psql.storage.*
import ru.tnsk.backend.data.network.storage.NskgtStorage

val storageModule = module {
    factory { UserStorage(get()) }
    factory { RouteStorage(get()) }
    factory { TraceStorage(get()) }
    factory { StopStorage(get()) }
    factory { NskgtStorage(get()) }
    factory { DriverStorage(get()) }
    factory { TransportHistoryStorage(get()) }
}


