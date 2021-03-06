package com.example.nearby.data.repository

import com.example.nearby.data.datasource.LocationDeviceDataSource
import com.example.nearby.domain.LocationRepository

internal class LocationRepositoryImpl(private val locationDeviceDataSource: LocationDeviceDataSource) : LocationRepository {
    override fun getLocation() = locationDeviceDataSource.getLocation()
}