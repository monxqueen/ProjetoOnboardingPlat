package com.example.nearby.data

import com.example.nearby.data.model.UserLocationResponse
import io.reactivex.Single

interface LocationDeviceDataSource {
    fun getLocation() : Single<UserLocationResponse?>
}