package com.example.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.favorite.di.RemoteDataModule
import com.example.favorite.remote.utils.FileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpCookie
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private val robot = FavoriteFragmentRobot()
    private val fileReader = FileReader()
    private val mockWebServer: MockWebServer by lazy {
        MockWebServer()
    }

    @Before
    fun setup() {
        setupKoin()
        setupMockWebServer()
    }

    @After
    fun tearDown() {
        tearDownKoin()
        tearDownMockWebServer()
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

        val content = fileReader("assets/favoriteListSuccessResponse.json")

        val response = content?.let { MockResponse().setBody(it).setResponseCode(HttpURLConnection.HTTP_OK) }
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            scrollToItem("Magazine Luiza",  R.id.rvFavoriteStoresList)
            scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayEmptyListText() {

        val content = fileReader("assets/favoriteListEmptyResponse.json")

        val response = content?.let { MockResponse().setBody(it).setResponseCode(HttpURLConnection.HTTP_OK) }
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
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

    private fun setupKoin() {
        RemoteDataModule().load()
    }

    private fun setupMockWebServer() {
        mockWebServer.start(8080)
    }

    private fun tearDownKoin() {
        RemoteDataModule().unload()
    }

    private fun tearDownMockWebServer() {
        mockWebServer.shutdown()
    }
}