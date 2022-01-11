package com.example.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.DisposableViewModel
import com.example.favorite.domain.GetFavoriteListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class FavoriteViewModel(private val getFavoriteListUseCase: GetFavoriteListUseCase) : DisposableViewModel() {

    private val _viewStateLiveData = MutableLiveData<FavoriteViewState>()
    val viewStateLiveData: LiveData<FavoriteViewState> = _viewStateLiveData

    fun getFavoriteList() {
        getFavoriteListUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _viewStateLiveData.value = FavoriteViewState(isLoadingVisible = true)
            }
            .subscribe(
                {
                    _viewStateLiveData.value = FavoriteViewState(favoriteList = it)
                },
                {
                    _viewStateLiveData.value = FavoriteViewState(isErrorVisible = true)
                }
            ).handleDisposable()
    }

    fun tryAgain() {
        getFavoriteList()
    }
}