package com.example.store.data.remote.mapper

import com.example.store.data.remote.model.StoresResponse
import com.example.store.domain.entity.Stores

interface StoresMapper {

    fun mapStoresList(storesResponseList: List<StoresResponse>) : List<Stores> {
        return storesResponseList.map { mapStores(it) }
    }

    fun mapStores(storesResponse: StoresResponse) = Stores(
        id = storesResponse.id,
        name = storesResponse.name,
        iconUrl = storesResponse.iconUrl,
        latitude = storesResponse.latitude,
        longitude = storesResponse.longitude
    )
}