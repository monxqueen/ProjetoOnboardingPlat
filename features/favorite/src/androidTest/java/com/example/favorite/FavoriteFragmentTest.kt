package com.example.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.favorite.di.FavoriteModule
import com.example.favorite.domain.GetFavoriteListUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {
    private val robot = FavoriteFragmentRobot()

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
    fun whenFragmentIsStarted_shouldDisplayRecyclerView() {
        robot.launchFragment()
        robot.checkListVisibility(R.id.rvFavoriteStoresList)
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly() {
        robot.launchFragment()

        robot.scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
        robot.scrollToItem("Magalu",  R.id.rvFavoriteStoresList)
    }
}