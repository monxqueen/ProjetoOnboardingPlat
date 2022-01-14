package com.example.data.domain.entity

data class StoreDataSource(
    val id: Int,
    val name: String? = "",
    val iconUrl: String? = "",
    val latitude: Double,
    val longitude: Double
)