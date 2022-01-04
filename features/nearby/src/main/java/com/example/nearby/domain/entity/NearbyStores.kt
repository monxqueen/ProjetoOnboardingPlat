package com.example.nearby.domain.entity

data class NearbyStores(
    val id: Int,
    val name: String? = "",
    val iconUrl: String? = "",
    val latitude: Double,
    val longitude: Double
)
