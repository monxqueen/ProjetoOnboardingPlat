package com.example.favorite.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.favorite.domain.GetFavoriteListUseCase
import com.example.favorite.domain.entity.FavoriteStore
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FavoriteViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val schedulers = RxSchedulerRule()

    private val getFavoriteListUseCase: GetFavoriteListUseCase = mock()
    private val viewModel = FavoriteViewModel(getFavoriteListUseCase)

    private val storeLiveData : Observer<List<FavoriteStore>> = mock()

    @Test
    fun `when call getFavoriteList should return an expected list`() {

        //given
        val favoriteList = fetchFavoriteList()

        whenever(getFavoriteListUseCase.invoke()).thenReturn(Single.just(favoriteList))

        val expected = listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )

        viewModel.storeLiveData.observeForever(storeLiveData)

        //when
        viewModel.getFavoriteList()

        //then
        verify(storeLiveData).onChanged(expected)
    }

    private fun fetchFavoriteList() =
        listOf(
            FavoriteStore(
                2,
                "Lojas Americanas",
                "icone.jpg"
            )
        )

    private fun getEmptyList() = emptyList<FavoriteStore>()
}