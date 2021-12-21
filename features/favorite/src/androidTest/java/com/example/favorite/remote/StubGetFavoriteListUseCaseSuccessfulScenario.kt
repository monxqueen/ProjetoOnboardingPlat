package com.example.favorite.remote

import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single

object StubGetFavoriteListUseCaseSuccessfulScenario : GetFavoriteListUseCase {
    override fun invoke() = Single.just(
        listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "https://s2.glbimg.com/Dgf_qkF--yAtoBEDeaPPVWmLvOg=/0x20:650x397/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_63b422c2caee4269b8b34177e8876b93/internal_photos/bs/2019/A/g/cQ3xPVTaee8zHQ4549fw/lojasamericanas.jpg"
            ),
            FavoriteStore(
                3,
                "Magalu",
                "https://imgsapp.impresso.correioweb.com.br/app/da_impresso_130686904244/2020/03/30/325011/20200329211503546282i.jpg"
            )
        )
    )
}