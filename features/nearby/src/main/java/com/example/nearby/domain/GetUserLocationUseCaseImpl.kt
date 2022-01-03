package com.example.nearby.domain

class GetUserLocationUseCaseImpl(private val locationRepository: LocationRepository ) :
    GetUserLocationUseCase {
     override operator fun invoke() = locationRepository.getLocation()
}

