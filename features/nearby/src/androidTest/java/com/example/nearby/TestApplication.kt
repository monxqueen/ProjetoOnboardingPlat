package com.example.nearby

import android.app.Application
import com.example.data.di.DataModule
import com.example.nearby.di.NearbyModule
import com.example.nearby.di.TestModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)

            DataModule().load()
            NearbyModule().load()
            TestModule().load()
        }
    }
}