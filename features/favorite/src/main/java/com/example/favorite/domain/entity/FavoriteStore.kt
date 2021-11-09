package com.example.favorite.domain.entity

data class FavoriteStore(
    val id: Int,
    val name: String? = "",
    val iconUrl: String? = ""
)