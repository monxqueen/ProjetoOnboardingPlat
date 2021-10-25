package com.example.store.data.model

import kotlinx.serialization.SerialName

data class StoreResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("iconUrl")
    val iconUrl: String,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double
)
