package com.example.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.di.RemoteDataModule
import com.example.favorite.remote.utils.FileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private val robot = FavoriteFragmentRobot()
    private lateinit var mockWebServer : MockWebServer

    @Before
    fun setup() {
        startKoin {
            RemoteDataModule().load()
        }

        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        stopKoin()
        mockWebServer.shutdown()
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly1() {

        val content = FileReader("favoriteListEmptyResponse.json").content

        val response = MockResponse().setBody(content).setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(response)

        robot.apply {
            launchFragment()
            scrollToItem("Magazine Luiza",  R.id.rvFavoriteStoresList)
            scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
        }
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