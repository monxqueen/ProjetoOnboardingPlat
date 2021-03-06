package com.example.data.di

import com.example.data.data.remote.RetrofitBuilder
import com.example.data.data.remote.StoreService
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoresListDataSourceUseCase
import com.example.data.domain.GetStoresListDataSourceUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

class DataModule {

    private val modules = module {

        // DATA
        single { RetrofitBuilder().buildRetrofit() }

        // DOMAIN
        factory<GetStoresListDataSourceUseCase> { GetStoresListDataSourceUseCaseImpl(
            repository = RepositoryImpl(
                storesMapper = StoresMapper(),
                service = get<Retrofit>().create(StoreService::class.java)
            )) }
    }

    fun load() = loadKoinModules(modules)
}