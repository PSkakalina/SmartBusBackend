package ru.tnsk.backend.di

import org.koin.dsl.module
import ru.tnsk.backend.data.network.HttpClientFactory
import ru.tnsk.backend.data.network.api.NskgtApi

val networkModule = module {
    factory { HttpClientFactory().create() }

    factory { NskgtApi(get()) }
}