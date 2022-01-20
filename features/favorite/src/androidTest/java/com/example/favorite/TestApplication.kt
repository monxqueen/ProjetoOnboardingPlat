package com.example.favorite

import android.app.Application
import com.example.data.di.DataModule
import com.example.favorite.di.FavoriteModule
import com.example.favorite.di.TestModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)

            DataModule().load()
            FavoriteModule().load()
            TestModule().load()
        }
    }
}