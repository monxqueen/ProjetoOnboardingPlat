package com.example.storeapp

import android.app.Application
import com.example.data.di.DataModule
import com.example.favorite.di.FavoriteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidContext(this@App)

            DataModule().load()
            FavoriteModule().load()
        }
    }
}