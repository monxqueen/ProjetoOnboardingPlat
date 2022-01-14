package com.example.data.domain

import com.example.data.domain.entity.StoreDataSource
import io.reactivex.Single

interface GetStoresListDataSourceUseCase {
    operator fun invoke(): Single<List<StoreDataSource>>
}