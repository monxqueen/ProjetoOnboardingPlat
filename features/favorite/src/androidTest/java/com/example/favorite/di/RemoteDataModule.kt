package com.example.favorite.di

import com.example.favorite.remote.StubRetrofitBuilder
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class RemoteDataModule {

    private val modules = module {

        // DATA
        single(override = true) { StubRetrofitBuilder().buildRetrofit() }
    }

    fun load() = loadKoinModules(modules)
}