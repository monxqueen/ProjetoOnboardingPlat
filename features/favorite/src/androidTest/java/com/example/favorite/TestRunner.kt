package com.example.favorite

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.data.di.DataModule
import com.example.favorite.di.FavoriteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)

            DataModule().load()
            FavoriteModule().load()
        }
    }
}