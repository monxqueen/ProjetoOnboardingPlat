package com.example.store.domain

import com.example.store.data.remote.repository.RepositoryImpl
import com.example.store.domain.entity.Store
import io.reactivex.Single

class GetStoreListUseCase(private val repository: Repository = RepositoryImpl()) {
    //val repository: Repository = RepositoryImpl()
    fun getList() = repository.getStores()
}