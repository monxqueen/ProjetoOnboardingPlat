package com.example.store.domain

import com.example.store.domain.model.Store
import io.reactivex.Single

interface StoresRepository {
    fun getStores(): Single<List<Store>>
}