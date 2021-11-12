package com.example.favorite.domain

import com.example.data.domain.GetStoreListUseCase
import com.example.data.domain.entity.Store
import com.example.favorite.domain.entity.FavoriteStore
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FavoriteDomainTest {

    private val favoriteStoresMapper = FavoriteStoresMapper()
    private val getStoreListUseCase : GetStoreListUseCase = mock()
    private val getFavoriteListUseCase = GetFavoriteListUseCaseImpl(getStoreListUseCase, favoriteStoresMapper)

    @Test
    fun `when call getFavoriteList should return an expected list`() {

        //given
        val storeList = fetchStoreList()

        whenever(getStoreListUseCase.getList()).thenReturn(Single.just(storeList))

        val expected = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )

        //when
        val result = getFavoriteListUseCase.invoke()

        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call mapStoresListToDomain should return a FavoriteStore-typed list`() {

        //given
        val expected = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )
        val storeList = listOf(
            Store(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )

        //when
        val result = favoriteStoresMapper.mapStoresListToDomain(storeList)

        //then
        assertEquals(expected, result)
    }

    private fun fetchStoreList() =
        listOf(
        Store(
            2,
            "Lojas Americanas",
            "icone.jpg",
            -141545.05264,
            -2545875.56450
        )
    )

    private fun fetchEmptyList() = emptyList<FavoriteStore>()
}