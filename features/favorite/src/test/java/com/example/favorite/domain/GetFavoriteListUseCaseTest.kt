package com.example.favorite.domain

import com.example.data.domain.GetStoresListDataSourceUseCase
import com.example.data.domain.entity.StoreDataSource
import com.example.favorite.domain.entity.FavoriteStore
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import io.reactivex.Single
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetFavoriteListUseCaseTest {

    private val favoriteStoresMapper = FavoriteStoresMapper()
    private val getStoresListDataSourceUseCase : GetStoresListDataSourceUseCase = mock()
    private val getFavoriteListUseCase = GetFavoriteListUseCaseImpl(getStoresListDataSourceUseCase, favoriteStoresMapper)

    @Test
    fun `when call getFavoriteList should return an expected list`() {
        //given
        val storeList = fetchStoreList()

        whenever(getStoresListDataSourceUseCase()).thenReturn(Single.just(storeList))

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
    fun `when call getFavoriteList should return an empty list`() {
        //given
        val emptyStoreList = fetchEmptyList()

        whenever(getStoresListDataSourceUseCase()).thenReturn(Single.just(emptyStoreList))

        val expected = emptyList<FavoriteStore>()

        //when
        val result = getFavoriteListUseCase.invoke()

        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call getFavoriteList should return a different object than expected`() {
        //given
        val differentStoreList = fetchDifferentStoreList()

        whenever(getStoresListDataSourceUseCase()).thenReturn(Single.just(differentStoreList))

        val expected = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg",
            )
        )

        //when
        val result = getFavoriteListUseCase.invoke()

        //then
        Assert.assertNotEquals(result.test().assertNoErrors(), expected)
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
            StoreDataSource(
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
        StoreDataSource(
            2,
            "Lojas Americanas",
            "icone.jpg",
            -141545.05264,
            -2545875.56450
        )
    )

    private fun fetchEmptyList() = emptyList<StoreDataSource>()

    private fun fetchDifferentStoreList() =
        listOf(
            StoreDataSource(
                2,
                "Magalu",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )
}