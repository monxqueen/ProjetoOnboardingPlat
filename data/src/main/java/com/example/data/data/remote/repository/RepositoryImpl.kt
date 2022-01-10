package com.example.data.data.remote.repository

import com.example.data.data.remote.StoreService
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.domain.Repository
import com.example.data.domain.entity.Store
import io.reactivex.Single

internal class RepositoryImpl(private val storesMapper: StoresMapper, private val service: StoreService) : Repository {
    override fun getStores() : Single<List<Store>> {
        return service.getStoresList().map { storesListResponse ->
            storesMapper.mapStoresListToDomain(storesListResponse) }
    }
}