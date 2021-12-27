package com.example.favorite.remote

import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single

object StubGetFavoriteListUseCaseEmptyListScenario : GetFavoriteListUseCase {
    override fun invoke() = Single.just(
        emptyList<FavoriteStore>()
    )
}