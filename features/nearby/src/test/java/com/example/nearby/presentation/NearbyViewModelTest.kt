package com.example.nearby.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common.utils.RxSchedulerRule
import com.example.nearby.domain.GetNearbyStoresUseCase
import com.example.nearby.domain.entity.NearbyStores
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class NearbyViewModelTest : KoinTest {

    private val getNearbyStoresUseCase: GetNearbyStoresUseCase = mock()
    private val viewStateLiveData : Observer<NearbyViewState> = mock()
    private val nearbyViewModel: NearbyViewModel by inject()

    private val koinModules = module {
        factory{ NearbyViewModel(getNearbyStoresUseCase) }
    }

    @get:Rule
    val setupKoin = KoinTestRule.create { modules(koinModules) }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val schedulers = RxSchedulerRule()

    @Test
    fun `when call getNearbyStores should return an expected list`() {
        //Given
        val nearbyList = fetchNearbyStores()
        whenever(getNearbyStoresUseCase()).thenReturn(Single.just(nearbyList))

        val loadingState = NearbyViewState(isLoadingVisible = true)

        val expectedNearbyList = listOf(
            NearbyStores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                distance = 0
            )
        )

        val expected = NearbyViewState(nearbyList = expectedNearbyList)

        nearbyViewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        nearbyViewModel.getNearbyStores()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    @Test
    fun `when call geNearbyStores should return an error`() {
        //Given
        val error = Throwable()
        whenever(getNearbyStoresUseCase()).thenReturn(Single.error(error))

        val loadingState = NearbyViewState(isLoadingVisible = true)

        val expected = NearbyViewState(isErrorVisible = true)

        nearbyViewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        nearbyViewModel.getNearbyStores()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    @Test
    fun `when call tryAgain should return an expected list`() {
        //Given
        val nearbyList = fetchNearbyStores()
        whenever(getNearbyStoresUseCase()).thenReturn(Single.just(nearbyList))

        val loadingState = NearbyViewState(isLoadingVisible = true)

        val expectedNearbyList = listOf(
            NearbyStores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                distance = 0
            )
        )

        val expected = NearbyViewState(nearbyList = expectedNearbyList)

        nearbyViewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        nearbyViewModel.tryAgain()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    @Test
    fun `when call tryAgain should return an error`() {
        //Given
        val error = Throwable()
        whenever(getNearbyStoresUseCase()).thenReturn(Single.error(error))

        val loadingState = NearbyViewState(isLoadingVisible = true)

        val expected = NearbyViewState(isErrorVisible = true)

        nearbyViewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        nearbyViewModel.tryAgain()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    private fun fetchNearbyStores() =
        listOf(
            NearbyStores(
                id = 2,
                name = "Lojas Americanas",
                iconUrl = "icone.jpg",
                distance = 0
            )
        )
}