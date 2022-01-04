package com.example.nearby.domain

import com.example.nearby.domain.entity.UserLocation
import io.reactivex.Single

interface GetUserLocationUseCase {
    operator fun invoke() : Single<UserLocation?>
}