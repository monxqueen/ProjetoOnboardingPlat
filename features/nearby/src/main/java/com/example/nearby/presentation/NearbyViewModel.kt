package com.example.nearby.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.DisposableViewModel
import com.example.nearby.domain.GetNearbyStoresUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class NearbyViewModel(private val getNearbyStoresUseCase: GetNearbyStoresUseCase) :
    DisposableViewModel() {

    private val _viewStateLiveData = MutableLiveData<NearbyViewState>()
    val viewStateLiveData: LiveData<NearbyViewState> = _viewStateLiveData

    private val _isLocationPermissionGrantedLiveData = MutableLiveData<Boolean>()

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

    fun updatePermissionStatus(granted: Boolean) {
        _isLocationPermissionGrantedLiveData.value = granted

        if (_isLocationPermissionGrantedLiveData.value == true) {
            getNearbyStores()
        }
    }
}