package com.example.nearby.domain

import com.example.nearby.data.model.UserLocation
import io.reactivex.Single

interface LocationRepository {
    fun getLocation() : Single<UserLocation?>
}