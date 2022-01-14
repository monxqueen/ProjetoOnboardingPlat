package com.example.nearby.domain

import com.example.nearby.domain.entity.NearbyStores
import com.example.nearby.domain.entity.Stores
import com.example.nearby.domain.entity.UserLocation
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

internal class GetNearbyStoresUseCaseTest : KoinTest {

    private val getStoresListUseCase: GetStoresListUseCase = mock()
    private val getUserLocationUseCase: GetUserLocationUseCase = mock()
    private val getNearbyStoresUseCase: GetNearbyStoresUseCase by inject()

    private val koinModules = module {
        factory<GetNearbyStoresUseCase> { GetNearbyStoresUseCaseImpl(getStoresListUseCase, getUserLocationUseCase) }
    }

    @get:Rule
    val setupKoin = KoinTestRule.create { modules(koinModules) }

    @Test
    fun `When call getNearbyStoreList should return a list of NearbyStores`() {
        // Given
        val storesList = fetchStoresList()
        whenever(getStoresListUseCase()).thenReturn(Single.just(storesList))

        val userLocation = fetchDeviceLocation()
        whenever(getUserLocationUseCase()).thenReturn(Single.just(userLocation))

        val expected = listOf(
            NearbyStores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                distance = 0
            )
        )

        // When
        val result = getNearbyStoresUseCase()

        // Then
        result.test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun `when call getNearbyStoreList should return an empty list`() {
        // Given
        val storesList = fetchEmptyList()
        whenever(getStoresListUseCase()).thenReturn(Single.just(storesList))

        val userLocation = fetchDeviceLocation()
        whenever(getUserLocationUseCase()).thenReturn(Single.just(userLocation))

        val expected = emptyList<NearbyStores>()

        // When
        val result = getNearbyStoresUseCase()

        // Then
        result.test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun `when call getNearbyStoreList should return a different object than expected`() {
        // Given
        val storesList = fetchDifferentStoresList()
        whenever(getStoresListUseCase()).thenReturn(Single.just(storesList))

        val userLocation = fetchDeviceLocation()
        whenever(getUserLocationUseCase()).thenReturn(Single.just(userLocation))

        val expected = listOf(
            NearbyStores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                distance = 0
            )
        )

        // When
        val result = getNearbyStoresUseCase()

        // Then
        Assert.assertNotEquals(result.test().assertNoErrors(), expected)
    }

    private fun fetchDeviceLocation() =
        UserLocation(
            latitude = -22.9101425,
            longitude = -47.0664706
        )

    private fun fetchStoresList() =
        listOf(
            Stores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                latitude = -23.562356,
                longitude = -46.6694725
            )
        )

    private fun fetchEmptyList() = emptyList<Stores>()

    private fun fetchDifferentStoresList() =
        listOf(
            Stores(
                id = 2,
                name = "Magazine Luiza",
                iconUrl = "icone.jpg",
                latitude = -23.562356,
                longitude = -46.6694725
            )
        )
}