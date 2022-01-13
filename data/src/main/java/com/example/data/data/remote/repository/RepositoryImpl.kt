package com.example.data.data.remote.repository

import com.example.data.data.remote.StoreService
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.domain.Repository
import com.example.data.domain.entity.StoreDataSource
import io.reactivex.Single

internal class RepositoryImpl(private val storesMapper: StoresMapper, private val service: StoreService) : Repository {
    override operator fun invoke() = service.getStoresList().map { storesListResponse ->
            storesMapper.mapStoresListToDomain(storesListResponse) }
}