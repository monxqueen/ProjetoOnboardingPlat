package com.example.favorite.di

import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoreListUseCase
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class FavoriteFeatureModule : FeatureModule() {
    override val domainModule = module {
        factory {
            GetFavoriteListUseCaseImpl(
                GetStoreListUseCaseImpl(RepositoryImpl(StoresMapper())),
                FavoriteStoresMapper()
            )
        }
    }

    override val presentationModule = module {
        viewModel {
            FavoriteViewModel(getFavoriteListUseCase = get())
        }
    }
}
