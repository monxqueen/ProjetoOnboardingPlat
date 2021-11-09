package com.example.store.domain

import com.example.store.domain.entity.Store
import io.reactivex.Single

interface Repository {
    fun getStores(): Single<List<Store>>
}