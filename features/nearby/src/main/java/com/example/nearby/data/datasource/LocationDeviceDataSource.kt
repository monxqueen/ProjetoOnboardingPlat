package com.example.nearby.data.datasource

import com.example.nearby.data.model.UserLocationResponse
import io.reactivex.Single

internal interface LocationDeviceDataSource {
    fun getLocation() : Single<UserLocationResponse>
}