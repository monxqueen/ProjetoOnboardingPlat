package com.example.data.data.remote.mapper

import com.example.store.data.remote.model.StoreResponse
import com.example.store.domain.entity.Store

class StoresMapper {

    fun mapStoresListToDomain(storesResponseList: List<StoreResponse>) : List<Store> {
        return storesResponseList.map { it.mapToDomain() }
    }

    private fun StoreResponse.mapToDomain() : Store {
        return Store(
            id = id,
            name = name,
            iconUrl = iconUrl,
            latitude = latitude,
            longitude = longitude
        )
    }
}