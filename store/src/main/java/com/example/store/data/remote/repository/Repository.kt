package com.example.store.data.remote.repository

import com.example.store.data.remote.Network
import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.domain.entity.Stores
import io.reactivex.Observable

class Repository(
    private val storesMapper: StoresMapper
) {

    fun fetchStoresList() : Observable<List<Stores>> {
        return Network.getService().getStoresList().map { storesMapper.mapStoresList(it) }
    }
}
