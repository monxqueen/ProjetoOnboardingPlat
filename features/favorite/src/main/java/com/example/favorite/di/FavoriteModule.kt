package com.example.favorite.di

import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class FavoriteModule {

    private val modules = module {

        // DOMAIN
        factory<GetFavoriteListUseCase> { GetFavoriteListUseCaseImpl(
            storeListUseCase = get(),
            favoriteStoresMapper = FavoriteStoresMapper())
        }

        // PRESENTATION
        viewModel {
            FavoriteViewModel(
                getFavoriteListUseCase = get())
        }
    }

    fun load() = loadKoinModules(modules)
}
