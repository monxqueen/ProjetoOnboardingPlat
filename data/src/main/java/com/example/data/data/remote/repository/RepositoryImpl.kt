package com.example.data.data.remote.repository

import com.example.data.data.remote.Network
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.domain.Repository
import com.example.data.domain.entity.Store
import io.reactivex.Single

class RepositoryImpl(private val storesMapper: StoresMapper) : Repository {

    override fun getStores() : Single<List<Store>> {
        return Network.retrofitService.getStoresList().map { storesListResponse ->
            storesMapper.mapStoresListToDomain(storesListResponse) }
    }
}

