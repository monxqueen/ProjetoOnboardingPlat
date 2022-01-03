package com.example.nearby.domain

import com.example.nearby.data.model.UserLocation
import io.reactivex.Single

interface GetUserLocationUseCase {
    operator fun invoke() : Single<UserLocation?>
}