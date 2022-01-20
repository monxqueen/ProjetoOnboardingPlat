package com.example.nearby.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.DisposableViewModel
import com.example.nearby.domain.GetNearbyStoresUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

 private const val coarseLocationPermission = "android.permission.ACCESS_COARSE_LOCATION"
 private const val fineLocationPermission = "android.permission.ACCESS_FINE_LOCATION"

internal class NearbyViewModel(private val getNearbyStoresUseCase: GetNearbyStoresUseCase) :
    DisposableViewModel() {

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

    fun validatePermission(permissions: Map<String, Boolean>) {
        val isGranted =
            permissions[coarseLocationPermission] ?: false && permissions[fineLocationPermission] ?: false

        if (isGranted) {
            getNearbyStores()
        } else {
            _viewStateLiveData.value = NearbyViewState(isPermissionErrorVisible = true)
        }
    }
}