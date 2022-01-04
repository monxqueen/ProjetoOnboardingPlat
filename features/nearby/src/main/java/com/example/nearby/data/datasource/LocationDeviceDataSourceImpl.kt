package com.example.nearby.data.datasource

import android.annotation.SuppressLint
import android.content.Context
import com.example.nearby.data.model.UserLocationResponse
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

internal class LocationDeviceDataSourceImpl(private val context: Context) : LocationDeviceDataSource {

    //TODO: Levar o tratamento de permissões pra ViewModel
    @SuppressLint("MissingPermission")
    override fun getLocation(): Single<UserLocationResponse> {

        lateinit var userLocation : UserLocationResponse

        return Single.create { emmiter ->
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { apiLocation ->
                apiLocation?.let {
                    userLocation = UserLocationResponse(
                        latitude = apiLocation.latitude,
                        longitude = apiLocation.longitude
                    )
                }
                emmiter.onSuccess(userLocation)
            }
        }
    }
}