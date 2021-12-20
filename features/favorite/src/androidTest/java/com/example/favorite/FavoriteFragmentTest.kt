package com.example.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.favorite.di.FavoriteModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private val robot = FavoriteFragmentRobot()

    @Before
    fun setupKoin() {
        startKoin {
            FavoriteModule().load()
        }
    }

    @After
    fun tearDownKoin() {
        stopKoin()
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerView() {
        robot.apply {
            loadModulesOfSuccessfulScenario()
            launchFragment()
            checkVisibility(R.id.rvFavoriteStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly() {
        robot.apply {
            loadModulesOfSuccessfulScenario()
            launchFragment()
            scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
            scrollToItem("Magalu",  R.id.rvFavoriteStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayEmptyListText() {
        robot.apply {
            loadModulesOfEmptyListScenario()
            launchFragment()
            checkVisibility(R.id.txtEmptyResult)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayError() {
        robot.apply {
            loadModulesOfErrorScenario()
            launchFragment()
            checkVisibility(R.id.includeLayoutError)
        }
    }

    @Test
    fun whenTryAgainButtonIsPressed_shouldDisplayRecyclerView() {
        robot.apply {
            loadModulesOfErrorScenario()
            launchFragment()
            clickOnButton(R.id.btn_error)
            loadModulesOfSuccessfulScenario()
            launchFragment()
            checkVisibility(R.id.rvFavoriteStoresList)
        }
    }
}