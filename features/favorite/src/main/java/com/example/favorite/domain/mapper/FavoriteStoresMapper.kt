package com.example.favorite.domain.mapper

import com.example.data.domain.entity.StoreDataSource
import com.example.favorite.domain.entity.FavoriteStore

internal class FavoriteStoresMapper {

    fun mapStoresListToDomain(storesList: List<StoreDataSource>) : List<FavoriteStore> {
        return storesList.map { it.mapToDomain() }
    }

    private fun StoreDataSource.mapToDomain() : FavoriteStore {
        return FavoriteStore(
            id = id,
            name = name,
            iconUrl = iconUrl
        )
    }
}