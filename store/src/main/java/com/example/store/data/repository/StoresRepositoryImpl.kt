package com.example.store.data.repository

import com.example.store.data.base.Network
import com.example.store.data.mapper.StoreMapper
import com.example.store.data.mapper.StoreMapperImpl
import com.example.store.domain.StoresRepository
import com.example.store.domain.model.Store
import io.reactivex.Single

class StoresRepositoryImpl(private val storeMapper: StoreMapper): StoresRepository {

    override fun getStores(): Single<List<Store>> {
        return Network.getRemoteSource()
            .getStoresList()
            .map {
                storeMapper.map(it)
            }
    }

}