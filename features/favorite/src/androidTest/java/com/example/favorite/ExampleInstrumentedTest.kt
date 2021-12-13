package com.example.favorite

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.di.FavoriteModule
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.favorite.test", appContext.packageName)
    }
}