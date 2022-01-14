package com.example.nearby.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat

class Permission {
    companion object{

        private fun checkPermissions() {
            if (ActivityCompat.checkSelfPermission(
                    ContentProviderCompat.requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    ContentProviderCompat.requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions()
                return
            }
        }
    }
}