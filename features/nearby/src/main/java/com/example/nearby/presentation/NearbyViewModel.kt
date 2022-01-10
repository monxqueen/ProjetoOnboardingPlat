package com.example.nearby.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearby.domain.GetNearbyStoresUseCase
import com.example.nearby.presentation.utils.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class NearbyViewModel(private val getNearbyStoresUseCase: GetNearbyStoresUseCase) : DisposableViewModel() {

    private val _viewStateLiveData = MutableLiveData<NearbyViewState>()
    val viewStateLiveData: LiveData<NearbyViewState> = _viewStateLiveData

    fun getNearbyStores() {
        getNearbyStoresUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _viewStateLiveData.value = NearbyViewState(isLoadingVisible = true)
            }
            .subscribe(
                {
                    _viewStateLiveData.value = NearbyViewState(nearbyList = it)
                },
                {
                    Log.e("Erro", "${it.cause}")
                    _viewStateLiveData.value = NearbyViewState(isErrorVisible = true)
                }
            ).handleDisposable()
    }

    fun tryAgain() {
        getNearbyStores()
    }
}