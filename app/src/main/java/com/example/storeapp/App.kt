package com.example.storeapp

import android.app.Application
import com.example.favorite.di.FavoriteFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            FavoriteFeatureModule().load()
        }
    }
}