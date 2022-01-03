package com.example.nearby.data

import com.example.nearby.data.model.UserLocation
import io.reactivex.Single

interface LocationDeviceDataSource {
    fun getLocation() : Single<UserLocation?>
}