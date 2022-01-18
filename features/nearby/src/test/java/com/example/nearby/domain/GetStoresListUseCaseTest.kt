package com.example.nearby.domain

import com.example.data.domain.GetStoresListDataSourceUseCase
import com.example.data.domain.entity.StoreDataSource
import com.example.nearby.domain.entity.Stores
import com.example.nearby.domain.mapper.StoresMapper
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetStoresListUseCaseTest : KoinTest {

    private val getStoresDataSourceListUseCase: GetStoresListDataSourceUseCase = mock()
    private val getStoresListUseCase : GetStoresListUseCase by inject()

    private val koinModules = module {
        factory<GetStoresListUseCase> { GetStoresListUseCaseImpl(getStoresDataSourceListUseCase, StoresMapper()) }
    }

    @get:Rule
    val setupKoin = KoinTestRule.create { modules(koinModules) }

    @Test
    fun `When call getStoresListUseCase should return an expected list`() {
        // Given
        val storesDataSourceList = fetchStoresDataSourceList()
        whenever(getStoresDataSourceListUseCase()).thenReturn(Single.just(storesDataSourceList))

        val expected = listOf(
            Stores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                latitude = -23.562356,
                longitude = -46.6694725
            )
        )

        // When
        val result = getStoresListUseCase()

        // Then
        result.test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun `When call getStoresListUseCase should return an empty list`() {
        // Given
        val emptyStoresDataSourceList = fetchStoresDataSourceEmptyList()
        whenever(getStoresDataSourceListUseCase()).thenReturn(Single.just(emptyStoresDataSourceList))

        val expected = emptyList<Stores>()

        // When
        val result = getStoresListUseCase()

        // Then
        result.test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun `When call getStoresListUseCase should return a different object than expected`() {
        // Given
        val storesDataSourceList = fetchDifferentStoresDataSourceList()
        whenever(getStoresDataSourceListUseCase()).thenReturn(Single.just(storesDataSourceList))

        val expected = listOf(
            Stores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                latitude = -23.562356,
                longitude = -46.6694725
            )
        )

        // When
        val result = getStoresListUseCase()

        // Then
        Assert.assertNotEquals(result.test().assertNoErrors(), expected)
    }

    private fun fetchStoresDataSourceList() = listOf(
        StoreDataSource(
            id = 2,
            name = "Lojas Americanas",
            iconUrl = "icone.jpg",
            latitude = -23.562356,
            longitude = -46.6694725
        )
    )

    private fun fetchStoresDataSourceEmptyList() = emptyList<StoreDataSource>()

    private fun fetchDifferentStoresDataSourceList() =
        listOf(
            StoreDataSource(
                id = 2,
                name = "Magazine Luiza",
                iconUrl = "icone.jpg",
                latitude = -23.562356,
                longitude = -46.6694725
            )
        )
}