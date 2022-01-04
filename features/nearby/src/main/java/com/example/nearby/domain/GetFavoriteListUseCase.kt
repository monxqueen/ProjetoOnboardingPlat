package com.example.nearby.domain

import com.example.nearby.domain.entity.FavoriteStores
import io.reactivex.Single

interface GetFavoriteListUseCase {
    operator fun invoke() : Single<List<FavoriteStores>>
}