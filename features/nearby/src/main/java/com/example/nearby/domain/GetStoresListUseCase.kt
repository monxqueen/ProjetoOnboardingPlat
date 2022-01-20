package com.example.nearby.domain

import com.example.nearby.domain.entity.Stores
import io.reactivex.Single

internal interface GetStoresListUseCase {
    operator fun invoke() : Single<List<Stores>>
}