package com.example.store.domain

import com.example.store.domain.entity.Store
import io.reactivex.Single

interface GetStoreListUseCase {
    fun getList(): Single<List<Store>>
}