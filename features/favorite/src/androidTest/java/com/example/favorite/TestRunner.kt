package com.example.favorite

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.data.di.DataModule
import com.example.favorite.di.FavoriteModule
import com.example.favorite.di.TestModule
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class TestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }

    override fun onStart() {

        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("RxJava 2.x IO Scheduler"))

        super.onStart()
    }
}