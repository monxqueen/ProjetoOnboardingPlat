package com.example.nearby.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.view.InflateException
import androidx.core.app.ActivityCompat
import com.example.nearby.data.model.UserLocation
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationDeviceDataSourceImpl(private val context: Context) : LocationDeviceDataSource {

    //TODO: gerenciar permissões
    //TODO: qual seria o trigger para executar essa função?
    override fun getLocation(): Single<UserLocation?> {
        var userLocation: UserLocation? = null
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw InflateException("Permissão não aceita pelo usuário")
        }

        LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { apiLocation ->
            apiLocation?.let {
                userLocation = UserLocation(
                    latitude = apiLocation.latitude,
                    longitude = apiLocation.longitude
                )
            }
        }
        return Single.just(userLocation)
    }
}