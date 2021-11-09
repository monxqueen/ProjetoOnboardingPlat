package com.example.favorite.domain

import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.store.domain.GetStoreListUseCase

class GetFavoriteListUseCase(private val storeListUseCase: GetStoreListUseCase, private val favoriteStoresMapper: FavoriteStoresMapper ) {
    fun getFavoriteList() = storeListUseCase.getList().map { storeList ->
        favoriteStoresMapper.mapStoresListToDomain(storeList)
    }
}