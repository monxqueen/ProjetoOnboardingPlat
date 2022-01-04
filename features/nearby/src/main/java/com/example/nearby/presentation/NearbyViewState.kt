package com.example.nearby.presentation

import com.example.nearby.domain.entity.UserLocation

internal data class NearbyViewState(
    val userLocation: UserLocation? = null
)
