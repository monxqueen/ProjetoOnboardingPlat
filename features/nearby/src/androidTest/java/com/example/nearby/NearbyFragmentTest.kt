package com.example.nearby

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.common.utils.FileReader
import com.example.common.utils.RemoteDataModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import java.net.HttpURLConnection

private const val SUCCESS_RESPONSE_PATH = "assets/nearbyListSuccessResponse.json"
private const val EMPTY_RESPONSE_PATH = "assets/nearbyListEmptyResponse.json"

@RunWith(AndroidJUnit4::class)
class NearbyFragmentTest : KoinTest {

    private val robot: NearbyFragmentRobot by inject()
    private val fileReader: FileReader by inject()
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

        val response = getResponse(HttpURLConnection.HTTP_OK, SUCCESS_RESPONSE_PATH)
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            checkVisibility(R.id.rvStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly() {

        val response = getResponse(HttpURLConnection.HTTP_OK, SUCCESS_RESPONSE_PATH)
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            scrollToItem("Magazine Luiza",  R.id.rvStoresList)
            scrollToItem("Lojas Americanas",  R.id.rvStoresList)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayEmptyListText() {

        val response = getResponse(HttpURLConnection.HTTP_OK, EMPTY_RESPONSE_PATH)
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            checkVisibility(R.id.txtEmptyResult)
        }
    }

    @Test
    fun whenFragmentIsStarted_shouldDisplayError() {

        val errorResponse = setHttpCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mockWebServer.enqueue(errorResponse)

        robot.apply {
            launchFragment()
            checkVisibility(R.id.layoutError)
        }
    }

    @Test
    fun whenTryAgainButtonIsPressed_shouldDisplayRecyclerView() {

        val errorResponse = setHttpCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mockWebServer.enqueue(errorResponse)

        val response = getResponse(HttpURLConnection.HTTP_OK, SUCCESS_RESPONSE_PATH)
        response?.let { mockWebServer.enqueue(it) }

        robot.apply {
            launchFragment()
            clickOnButton(R.id.btnError)
            checkVisibility(R.id.rvStoresList)
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

    private fun tearDownKoin() {
        RemoteDataModule().unload()
    }

    private fun setupMockWebServer() {
        mockWebServer.start(8080)
    }

    private fun tearDownMockWebServer() {
        mockWebServer.shutdown()
    }
}