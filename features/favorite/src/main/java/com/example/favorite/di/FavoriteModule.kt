package com.example.favorite.di

import com.example.data.domain.GetStoreListUseCase
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class FavoriteModule() {

    private val modules = module {
        // DOMAIN
        factory { FavoriteStoresMapper() }
        factory<GetFavoriteListUseCase> { GetFavoriteListUseCaseImpl(
            storeListUseCase = get(),
            favoriteStoresMapper = get())
        }

        // PRESENTATION
        viewModel {
            FavoriteViewModel(
                getFavoriteListUseCase = get())
        }
    }

    fun load() = loadKoinModules(modules)
}
