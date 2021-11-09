package com.example.store.domain.entity

data class Store(
    val id: Int,
    val name: String? = "",
    val iconUrl: String? = "",
    val latitude: Double,
    val longitude: Double
)