package com.example.nearby.data

import com.example.nearby.data.datasource.LocationDeviceDataSource
import com.example.nearby.data.model.UserLocationResponse
import com.example.nearby.data.repository.LocationRepositoryImpl
import com.example.nearby.domain.LocationRepository
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

private const val LOCATION_ERROR_EXCEPTION = "Não foi possível pegar a localização do dispositivo."

internal class LocationRepositoryTest : KoinTest {

    private val locationDeviceDataSource: LocationDeviceDataSource = mock()
    private val locationRepository: LocationRepository by inject()

    private val koinModules = module {
        factory<LocationRepository> { LocationRepositoryImpl(locationDeviceDataSource) }
    }

    @get:Rule
    val setupKoin = KoinTestRule.create { modules(koinModules) }

    @Test
    fun `When call getLocation should return last known device location`() {
        // Given
        val deviceLocation = fetchDeviceLocation()

        whenever(locationDeviceDataSource.getLocation())
            .thenReturn(Single.just(deviceLocation))

        val expected = UserLocationResponse(
            latitude = -23.562356,
            longitude = -46.6694725
        )

        // When
        val result = locationRepository.getLocation()

        // Then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `When call getLocation should return an exception`() {
        // Given
        val exception = setLocationErrorException()

        whenever(locationDeviceDataSource.getLocation())
            .thenReturn(Single.error(exception))

        // When
        val result = locationRepository.getLocation()

        // Then
        result.test().assertErrorMessage("Não foi possível pegar a localização do dispositivo.")
    }

    private fun fetchDeviceLocation() =
        UserLocationResponse(
            latitude = -23.562356,
            longitude = -46.6694725
        )

    private fun setLocationErrorException() = Exception(LOCATION_ERROR_EXCEPTION)
}