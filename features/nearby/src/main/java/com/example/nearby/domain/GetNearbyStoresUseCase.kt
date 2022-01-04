package com.example.nearby.domain

import com.example.nearby.domain.entity.NearbyStores
import io.reactivex.Single

interface GetNearbyStoresUseCase {
    operator fun invoke() : Single<List<NearbyStores>>
}