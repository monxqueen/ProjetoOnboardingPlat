package com.example.favorite.domain

import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.data.domain.GetStoreListUseCase

class GetFavoriteListUseCaseImpl(private val storeListUseCase: GetStoreListUseCase,
                                 private val favoriteStoresMapper: FavoriteStoresMapper ) : GetFavoriteListUseCase {
    override operator fun invoke() = storeListUseCase.getList().map { storeList ->
        favoriteStoresMapper.mapStoresListToDomain(storeList)
    }
}