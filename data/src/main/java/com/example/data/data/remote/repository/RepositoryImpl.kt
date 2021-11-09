package com.example.store.data.remote.repository

import com.example.store.data.remote.Network
import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.domain.Repository
import com.example.store.domain.entity.Store
import io.reactivex.Single

class RepositoryImpl(private val storesMapper: StoresMapper) : Repository {

    override fun getStores() : Single<List<Store>> {
        return Network.retrofitService.getStoresList().map { storesListResponse ->
            storesMapper.mapStoresListToDomain(storesListResponse) }
    }
}

