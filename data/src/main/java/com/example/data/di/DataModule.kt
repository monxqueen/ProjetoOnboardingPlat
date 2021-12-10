package com.example.data.di

import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoreListUseCase
import com.example.data.domain.GetStoreListUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class DataModule {

    private val modules = module {

        // DATA
        factory { StoresMapper() }
        factory { RepositoryImpl(
            storesMapper = get()
        ) }

        // DOMAIN
        factory<GetStoreListUseCase> { GetStoreListUseCaseImpl(
            repository = get()) }
    }

    fun load() = loadKoinModules(modules)
}