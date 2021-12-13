package com.example.favorite

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.favorite.di.FavoriteModule
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.presentation.FavoriteFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import androidx.fragment.app.testing.launchFragmentInContainer
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    @Before
    fun setupKoin() {
        startKoin {  }
        FavoriteModule().load()
        loadKoinModules(
            module(override = true) {
                factory<GetFavoriteListUseCase> { StubGetFavoriteListUseCase }
            }
        )
    }

    @After
    fun tearDownKoin() {
       stopKoin()
    }

    @Test
    fun teste() {
        launchFragmentInContainer<FavoriteFragment>(initialState = Lifecycle.State.STARTED)
        onView(withId(R.id.rvFavoriteStoresList)).check(matches(isDisplayed()))
    }

}