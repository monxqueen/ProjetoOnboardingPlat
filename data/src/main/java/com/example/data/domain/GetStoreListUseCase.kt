package com.example.data.domain

import com.example.data.domain.entity.Store
import io.reactivex.Single

interface GetStoreListUseCase {
    fun getList(): Single<List<Store>>
}