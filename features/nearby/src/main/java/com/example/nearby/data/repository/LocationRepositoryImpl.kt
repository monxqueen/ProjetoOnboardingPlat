package com.example.nearby.data.repository

import com.example.nearby.data.LocationDeviceDataSource
import com.example.nearby.domain.LocationRepository

class LocationRepositoryImpl(private val locationDeviceDataSource: LocationDeviceDataSource) : LocationRepository {
    override fun getLocation() = locationDeviceDataSource.getLocation()
}