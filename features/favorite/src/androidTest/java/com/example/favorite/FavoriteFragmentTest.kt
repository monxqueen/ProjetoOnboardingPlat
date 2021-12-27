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

        val response = getResponse(HttpURLConnection.HTTP_OK, "assets/favoriteListSuccessResponse.json")
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            checkVisibility(R.id.rvFavoriteStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly() {

        val response = getResponse(HttpURLConnection.HTTP_OK, "assets/favoriteListSuccessResponse.json")
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            scrollToItem("Magazine Luiza",  R.id.rvFavoriteStoresList)
            scrollToItem("Lojas Americanas",  R.id.rvFavoriteStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayEmptyListText() {

        val response = getResponse(HttpURLConnection.HTTP_OK, "assets/favoriteListEmptyResponse.json")
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

        val errorResponse = setHttpCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mockWebServer.enqueue(errorResponse)

        val response = getResponse(HttpURLConnection.HTTP_OK, "assets/favoriteListSuccessResponse.json")
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            clickOnButton(R.id.btn_error)
            checkVisibility(R.id.rvFavoriteStoresList)
        }
    }

    private fun getResponse(statusCode: Int, file: String): MockResponse? {
        val content = fileReader(file)
        return content?.let { setHttpCode(statusCode).setBody(it) }
    }

    private fun setHttpCode(statusCode: Int) = MockResponse().setResponseCode(statusCode)

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