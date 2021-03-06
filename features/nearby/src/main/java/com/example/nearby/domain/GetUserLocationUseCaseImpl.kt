package com.example.nearby.domain

import com.example.nearby.data.model.UserLocationResponse
import com.example.nearby.domain.entity.UserLocation

internal class GetUserLocationUseCaseImpl(private val locationRepository: LocationRepository) :
    GetUserLocationUseCase {
    override operator fun invoke() = locationRepository.getLocation().map { userLocationResponse ->
        userLocationResponse.mapToDomain()
    }

    private fun UserLocationResponse.mapToDomain() = UserLocation(
        latitude = latitude,
        longitude = longitude
    )
}