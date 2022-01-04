package com.example.nearby.domain

import com.example.nearby.data.model.UserLocationResponse
import io.reactivex.Single

interface LocationRepository {
    fun getLocation() : Single<UserLocationResponse>
}