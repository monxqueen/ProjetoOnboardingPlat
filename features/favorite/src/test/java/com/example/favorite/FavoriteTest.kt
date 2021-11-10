package com.example.favorite

import com.example.data.domain.entity.Store
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FavoriteTest {

    private val getFavoriteListUseCase: GetFavoriteListUseCase = mock(GetFavoriteListUseCase::class.java)
    private val favoriteStoresMapper = FavoriteStoresMapper()

    @Test
    fun `when call getFavoriteList should return an expected list`() {
        //given
        val favoriteList = fetchFavoriteList()

        `when`(getFavoriteListUseCase.getFavoriteList()).thenReturn(Single.just(favoriteList))

        val expected = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )

        //when
        val result = getFavoriteListUseCase.getFavoriteList()

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
        Assert.assertEquals(expected, result)
    }

    private fun fetchFavoriteList() =
        listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )

    private fun getEmptyList() = emptyList<FavoriteStore>()

}