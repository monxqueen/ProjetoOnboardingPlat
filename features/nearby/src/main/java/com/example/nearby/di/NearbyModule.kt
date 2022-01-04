package com.example.nearby.di

import com.example.nearby.data.datasource.LocationDeviceDataSourceImpl
import com.example.nearby.data.repository.LocationRepositoryImpl
import com.example.nearby.domain.GetUserLocationUseCaseImpl
import com.example.nearby.presentation.NearbyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class NearbyModule {

    private val modules = module {

        viewModel {
            NearbyViewModel(
                getUserLocationUseCase = GetUserLocationUseCaseImpl(
                    locationRepository = LocationRepositoryImpl(
                        locationDeviceDataSource = LocationDeviceDataSourceImpl(
                            context = androidContext() //TODO: Faz sentido?
                        )
                    )
                )
            )
        }
    }

    fun load() = loadKoinModules(modules)
}