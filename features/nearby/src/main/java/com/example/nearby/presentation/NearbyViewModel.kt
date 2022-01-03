package com.example.nearby.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearby.domain.GetUserLocationUseCase
import com.example.nearby.presentation.utils.DisposableViewModel
import io.reactivex.schedulers.Schedulers

internal class NearbyViewModel(private val getUserLocationUseCase: GetUserLocationUseCase) : DisposableViewModel() {

    private val _viewStateLiveData = MutableLiveData<NearbyViewState>()
    val viewStateLiveData: LiveData<NearbyViewState> = _viewStateLiveData

    fun getUserLocation() {
        getUserLocationUseCase()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    _viewStateLiveData.value = NearbyViewState(userLocation = it)
                },
                {
                    Log.e("Deu ruim", "Ops, deu ruim")
                }
            ).handleDisposable()
    }
}