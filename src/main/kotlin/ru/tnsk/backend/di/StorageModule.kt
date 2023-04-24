package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.db.psql.storage.route.RouteStorage
import ru.tnsk.backend.data.db.psql.storage.stop.StopStorage
import ru.tnsk.backend.data.db.psql.storage.trace.TraceStorage
import ru.tnsk.backend.data.db.psql.storage.user.UserStorage
import ru.tnsk.backend.data.network.storage.NskgtStorage

val storageModule = module {
    factory { UserStorage(get()) }
    factory { RouteStorage(get()) }
    factory { TraceStorage(get()) }
    factory { StopStorage(get()) }
    factory { NskgtStorage(get()) }
}


