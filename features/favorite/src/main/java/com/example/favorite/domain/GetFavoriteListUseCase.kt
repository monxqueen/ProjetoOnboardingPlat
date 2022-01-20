package com.example.favorite.domain

import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single

internal interface GetFavoriteListUseCase {
    operator fun invoke() : Single<List<FavoriteStore>>
}