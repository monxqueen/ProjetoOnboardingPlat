package com.example.nearby.domain

import com.example.data.domain.GetStoreListUseCase
import com.example.nearby.domain.mapper.FavoriteStoresMapper

internal class GetFavoriteListUseCaseImpl(private val storeListUseCase: GetStoreListUseCase,
                                          private val favoriteStoresMapper: FavoriteStoresMapper
) : GetFavoriteListUseCase {
    override fun invoke() = storeListUseCase.getList().map { storeList ->
        favoriteStoresMapper.mapStoresListToDomain(storeList)
    }
}