package com.example.nearby.domain

import com.example.nearby.data.model.UserLocationResponse
import com.example.nearby.domain.entity.UserLocation
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetUserLocationUseCaseTest : KoinTest {

    private val locationRepository: LocationRepository = mock()
    private val getUserLocationoUseCase : GetUserLocationUseCase by inject()

    private val koinModules = module {
        factory<GetUserLocationUseCase> { GetUserLocationUseCaseImpl(locationRepository) }
    }

    @get:Rule
    val setupKoin = KoinTestRule.create { modules(koinModules) }

    @Test
    fun `When call getUserLocationoUseCase should return UserLocation`() {
        // Given
        val deviceLocationResponse = fetchDeviceLocationResponse()
        whenever(locationRepository.getLocation()).thenReturn(Single.just(deviceLocationResponse))

        val expected = UserLocation(
            latitude = -22.9101425,
            longitude = -47.0664706
        )

        // When
        val result = getUserLocationoUseCase()

        // Then
        result.test()
            .assertNoErrors()
            .assertValue(expected)
    }

    private fun fetchDeviceLocationResponse() =
        UserLocationResponse(
            latitude = -22.9101425,
            longitude = -47.0664706
        )
}