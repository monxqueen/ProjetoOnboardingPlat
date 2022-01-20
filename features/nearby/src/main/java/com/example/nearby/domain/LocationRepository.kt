package com.example.nearby.domain

import com.example.nearby.data.model.UserLocationResponse
import io.reactivex.Single

internal interface LocationRepository {
    fun getLocation() : Single<UserLocationResponse>
}