package com.example.favorite.di

import com.example.data.domain.GetStoreListUseCaseImpl
import org.koin.dsl.module

class FavoriteModule() {

    private val modules = module {
        // DOMAIN
        factory { GetStoreListUseCaseImpl(
            repository = get()) }

        // PRESENTATION

    }

//    val domainModule = module {
//        factory {
//            GetFavoriteListUseCaseImpl(
//                GetStoreListUseCaseImpl(RepositoryImpl(StoresMapper())),
//                FavoriteStoresMapper()
//            )
//        }
//    }
//
//    val presentationModule = module {
//        viewModel {
//            FavoriteViewModel(getFavoriteListUseCase = get())
//        }
//    }
}
