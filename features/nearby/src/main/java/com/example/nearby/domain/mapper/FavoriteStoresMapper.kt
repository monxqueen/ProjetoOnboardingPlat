package com.example.nearby.domain.mapper

import com.example.data.domain.entity.Store
import com.example.nearby.domain.entity.FavoriteStores

internal class FavoriteStoresMapper {

    fun mapStoresListToDomain(storesList: List<Store>) : List<FavoriteStores> {
        return storesList.map { it.mapToDomain() }
    }

    private fun Store.mapToDomain() : FavoriteStores {
        return FavoriteStores(
            id = id,
            name = name,
            iconUrl = iconUrl,
            latitude = latitude,
            longitude = longitude
        )
    }
}