package com.example.nearby.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.example.nearby.data.model.UserLocationResponse
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationDeviceDataSourceImpl(private val context: Context) : LocationDeviceDataSource {

    //TODO: Levar o tratamento de permissões pra ViewModel
    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<UserLocationResponse?> {

        var userLocation: UserLocationResponse? = null

        return Single.create { emmiter ->
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { apiLocation ->
                apiLocation?.let {
                    userLocation = UserLocationResponse(
                        latitude = apiLocation.latitude,
                        longitude = apiLocation.longitude
                    )
                }
                userLocation?.let { emmiter.onSuccess(it) }
            }
        }
    }
}