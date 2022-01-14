package com.example.favorite.domain

import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.data.domain.GetStoresListDataSourceUseCase

internal class GetFavoriteListUseCaseImpl(private val getStoresListDataSourceUseCase: GetStoresListDataSourceUseCase,
                                          private val favoriteStoresMapper: FavoriteStoresMapper ) : GetFavoriteListUseCase {
    override operator fun invoke() = getStoresListDataSourceUseCase().map { storeList ->
        favoriteStoresMapper.mapStoresListToDomain(storeList)
    }
}