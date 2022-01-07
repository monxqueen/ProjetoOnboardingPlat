package com.example.favorite.domain.mapper

import com.example.data.domain.entity.Store
import com.example.favorite.domain.entity.FavoriteStore

internal class FavoriteStoresMapper {

    fun mapStoresListToDomain(storesList: List<Store>) : List<FavoriteStore> {
        return storesList.map { it.mapToDomain() }
    }

    private fun Store.mapToDomain() : FavoriteStore {
        return FavoriteStore(
            id = id,
            name = name,
            iconUrl = iconUrl
        )
    }
}