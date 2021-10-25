package com.example.store.data.mapper

import com.example.store.data.model.StoreResponse
import com.example.store.domain.model.Store

class StoreMapper {
    fun map(storeResponse: List<StoreResponse>): List<Store>{
        lateinit var storeList: MutableList<Store>
        storeResponse.forEach {
            val storeModel = Store(
                id = it.id,
                name = it.name,
                iconUrl = it.iconUrl,
                latitude = it.latitude,
                longitude = it.longitude
            )
            storeList.add(storeModel)
        }
        return storeList
    }
}
