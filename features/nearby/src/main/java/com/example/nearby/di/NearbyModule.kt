package com.example.nearby.di

import com.example.nearby.data.datasource.LocationDeviceDataSourceImpl
import com.example.nearby.data.repository.LocationRepositoryImpl
import com.example.nearby.domain.GetNearbyStoresUseCaseImpl
import com.example.nearby.domain.GetStoresListUseCaseImpl
import com.example.nearby.domain.GetUserLocationUseCaseImpl
import com.example.nearby.domain.mapper.StoresMapper
import com.example.nearby.presentation.NearbyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class NearbyModule {

    private val modules = module {

        viewModel {
            NearbyViewModel(
                getNearbyStoresUseCase = GetNearbyStoresUseCaseImpl(
                    getStoresListUseCase = GetStoresListUseCaseImpl(
                        getStoresListDataSourceUseCase = get(),
                        storesMapper = StoresMapper()
                    ),
                    getUserLocationUseCase = GetUserLocationUseCaseImpl(
                        locationRepository = LocationRepositoryImpl(
                            locationDeviceDataSource = LocationDeviceDataSourceImpl(androidContext())
                        )
                    )
                )
            )
        }
    }

    fun load() = loadKoinModules(modules)
}