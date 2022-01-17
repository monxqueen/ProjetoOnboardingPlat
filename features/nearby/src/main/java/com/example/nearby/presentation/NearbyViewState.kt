package com.example.nearby.presentation

import com.example.nearby.domain.entity.NearbyStores

internal data class NearbyViewState(
    val nearbyList: List<NearbyStores>? = null,
    val isErrorVisible : Boolean = false,
    val isLoadingVisible: Boolean = false
)