package com.example.store.data.remote.mapper

import com.example.store.data.remote.model.StoreResponse
import com.example.store.domain.entity.Store

class StoresMapper {

    fun mapStoresListToDomain(storesResponseList: List<StoreResponse>) : List<Store> {
        return storesResponseList.map { it.mapToDomain() }
    }

    // exemplo: https://github.com/PicPay/picpay-android/pull/10584/commits/a6899b8aef603eee04128a8c5a484db5b63458ce
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