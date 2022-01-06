package com.example.nearby.domain.entity

internal data class NearbyStores(
    val id: Int,
    val name: String? = "",
    val iconUrl: String? = "",
    val distance: Float
)
