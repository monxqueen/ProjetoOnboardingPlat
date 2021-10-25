package com.example.store.data.remote.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class StoresResponse(

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
