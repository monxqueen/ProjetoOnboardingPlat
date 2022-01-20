package com.example.common.utils

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class RemoteDataModule {
    private val modules = module {

        // DATA
        single(override = true) { StubRetrofitBuilder().buildRetrofit() }
    }

    fun load() = loadKoinModules(modules)
    fun unload() = unloadKoinModules(modules)
}