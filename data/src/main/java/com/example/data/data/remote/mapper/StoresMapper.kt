package com.example.data.data.remote.mapper

import com.example.data.data.remote.model.StoreDataSourceResponse
import com.example.data.domain.entity.StoreDataSource

internal class StoresMapper {

    fun mapStoresListToDomain(storesResponseList: List<StoreDataSourceResponse>) : List<StoreDataSource> {
        return storesResponseList.map { it.mapToDomain() }
    }

    private fun StoreDataSourceResponse.mapToDomain() : StoreDataSource {
        return StoreDataSource(
            id = id,
            name = name,
            iconUrl = iconUrl,
            latitude = latitude,
            longitude = longitude
        )
    }
}