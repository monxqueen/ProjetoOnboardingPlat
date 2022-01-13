package com.example.favorite.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common.utils.RxSchedulerRule
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

class FavoriteViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val schedulers = RxSchedulerRule()

    private val getFavoriteListUseCase: GetFavoriteListUseCase = mock()
    private val viewModel = FavoriteViewModel(getFavoriteListUseCase)

    private val viewStateLiveData : Observer<FavoriteViewState> = mock()

    @Test
    fun `when call getFavoriteList should return an expected list`() {
        //Given
        val favoriteList = fetchFavoriteList()
        whenever(getFavoriteListUseCase.invoke()).thenReturn(Single.just(favoriteList))

        val loadingState = FavoriteViewState(isLoadingVisible = true)

        val expectedFavoriteList = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )
        val expected = FavoriteViewState(expectedFavoriteList)

        viewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        viewModel.getFavoriteList()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    @Test
    fun `when call getFavoriteList should return an error`() {
        //Given
        val error = Throwable()
        whenever(getFavoriteListUseCase.invoke()).thenReturn(Single.error(error))

        val loadingState = FavoriteViewState(isLoadingVisible = true)

        val expected = FavoriteViewState(isErrorVisible = true)

        viewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        viewModel.getFavoriteList()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    fun `when retrying to call getFavoriteList for the second time should return an expected list`() {
        //Given
        val favoriteList = fetchFavoriteList()
        whenever(getFavoriteListUseCase.invoke()).thenReturn(Single.just(favoriteList))

        val expectedFavoriteList = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )
        val loadingState = FavoriteViewState(isLoadingVisible = true)
        val expectedSuccess = FavoriteViewState(expectedFavoriteList)

        viewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        viewModel.tryAgain()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expectedSuccess)
        }
    }

    @Test
    fun `when retrying to call getFavoriteList for the second time should return an error`() {
        //Given
        val error = Throwable()
        whenever(getFavoriteListUseCase.invoke()).thenReturn(Single.error(error))

        val loadingState = FavoriteViewState(isLoadingVisible = true)
        val expected = FavoriteViewState(isErrorVisible = true)

        viewModel.viewStateLiveData.observeForever(viewStateLiveData)

        //When
        viewModel.tryAgain()

        //Then
        inOrder(viewStateLiveData) {
            verify(viewStateLiveData).onChanged(loadingState)
            verify(viewStateLiveData).onChanged(expected)
        }
    }

    private fun fetchFavoriteList() =
        listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )
}