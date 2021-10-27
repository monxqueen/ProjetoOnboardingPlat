package com.example.store.data.mapper

import com.example.store.data.model.StoreResponse
import com.example.store.domain.model.Store

interface StoreMapper {
    fun map(storeResponseList: List<StoreResponse>): List<Store>
}