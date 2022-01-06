package com.example.nearby.domain.mapper

import com.example.data.domain.entity.Store
import com.example.nearby.domain.entity.Stores

internal class StoresMapper {

    fun mapStoresListToDomain(storesList: List<Store>) : List<Stores> {
        return storesList.map { it.mapToDomain() }
    }

    private fun Store.mapToDomain() : Stores {
        return Stores(
            id = id,
            name = name,
            iconUrl = iconUrl,
            latitude = latitude,
            longitude = longitude
        )
    }
}