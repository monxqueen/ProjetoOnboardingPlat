package com.example.store.data.repository

import com.example.store.data.base.Network
import com.example.store.data.mapper.StoreMapper
import com.example.store.data.remote.StoresRemoteSource
import com.example.store.domain.StoresRepository
import com.example.store.domain.model.Store
import io.reactivex.Single

class StoresRepositoryImpl: StoresRepository {
    private val storesRemoteSource: StoresRemoteSource = Network.getRemoteSource()
    private val storeMapper = StoreMapper()

    override fun getStores(): Single<List<Store>> {
        return storesRemoteSource
            .getStoresList()
            .map {
                storeMapper.map(it)
            }
    }

}