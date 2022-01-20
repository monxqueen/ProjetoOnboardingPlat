package com.example.data.domain

import com.example.data.domain.entity.StoreDataSource
import io.reactivex.Single

internal interface Repository {
    operator fun invoke(): Single<List<StoreDataSource>>
}