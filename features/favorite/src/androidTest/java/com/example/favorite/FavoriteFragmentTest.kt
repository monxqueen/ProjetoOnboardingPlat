package com.example.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.di.DataModule
import com.example.favorite.di.FavoriteModule
import com.example.favorite.di.RemoteDataModule
import com.example.favorite.remote.StubRetrofitBuilder
import com.example.favorite.remote.utils.FileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.*
import org.koin.dsl.module
import org.koin.test.KoinTest
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest : KoinTest {

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
    fun whenFragmentIsStarted_shouldDisplayRecyclerViewItemsCorrectly1() {

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

    private fun getOverriddenModules() = module(override = true) {
        single { StubRetrofitBuilder().buildRetrofit()}
    }

    private fun setupKoin() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                DataModule().load()
                RemoteDataModule().load()
                FavoriteModule().load()
            }
        }

//        loadKoinModules(getOverriddenModules())
    }

    private fun setupMockWebServer() {
        mockWebServer.start(8080)
    }

    private fun tearDownKoin() {
        stopKoin()
//        unloadKoinModules(getOverriddenModules())
    }

    private fun tearDownMockWebServer() {
        mockWebServer.shutdown()
    }
}