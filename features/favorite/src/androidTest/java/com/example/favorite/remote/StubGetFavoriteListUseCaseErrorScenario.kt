package com.example.favorite.remote

import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single

object StubGetFavoriteListUseCaseErrorScenario : GetFavoriteListUseCase {
    override fun invoke() = Single.error<List<FavoriteStore>>(Throwable())
}