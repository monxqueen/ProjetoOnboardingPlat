package com.example.data.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(

    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String? = "",
    @SerialName("iconUrl")
    val iconUrl: String? = "",
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double
)