package com.example.favorite.presentation

import com.example.favorite.domain.entity.FavoriteStore

internal data class FavoriteViewState(
    val favoriteList: List<FavoriteStore>? = null,
    val isErrorVisible : Boolean = false,
    val isLoadingVisible: Boolean = false)
