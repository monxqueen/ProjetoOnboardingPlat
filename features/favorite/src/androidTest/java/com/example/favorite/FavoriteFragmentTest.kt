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
        robot.loadModulesOfSuccessfulScenario()
        robot.launchFragment()
        robot.checkVisibility(R.id.rvFavoriteStoresList)
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly() {
        robot.loadModulesOfSuccessfulScenario()
        robot.launchFragment()
        robot.scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
        robot.scrollToItem("Magalu",  R.id.rvFavoriteStoresList)
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayEmptyListText() {
        robot.loadModulesOfEmptyListScenario()
        robot.launchFragment()
        robot.checkVisibility(R.id.txtEmptyResult)
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayError() {
        robot.loadModulesOfErrorScenario()
        robot.launchFragment()
        robot.checkVisibility(R.id.includeLayoutError)
    }

    @Test
    fun whenTryAgainButtonIsPressed_shouldDisplayRecyclerView() {
        robot.loadModulesOfErrorScenario()
        robot.launchFragment()
        robot.clickOnButton(R.id.btn_error)
        robot.loadModulesOfSuccessfulScenario()
        robot.launchFragment()
        robot.checkVisibility(R.id.rvFavoriteStoresList)
    }


}