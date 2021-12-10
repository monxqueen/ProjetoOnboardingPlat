package com.example.favorite.di

import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class FavoriteModule() {

    private val modules = module {
        // DOMAIN
        factory { GetStoreListUseCaseImpl(
            repository = get())
        }

        // PRESENTATION
        viewModel {
            FavoriteViewModel(
                getFavoriteListUseCase = get())
        }
    }

    fun load() = loadKoinModules(modules)
}
