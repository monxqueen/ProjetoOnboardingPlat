package com.example.nearby.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.utils.DisposableViewModel
import com.example.nearby.domain.GetNearbyStoresUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

  internal class NearbyViewModel(private val getNearbyStoresUseCase: GetNearbyStoresUseCase) : DisposableViewModel() {

      private val _viewStateLiveData = MutableLiveData<NearbyViewState>()
      val viewStateLiveData: LiveData<NearbyViewState> = _viewStateLiveData

      private val _viewStatePermissions = MutableLiveData<NearbyViewState>()
      val _viewStatePermission: LiveData<NearbyViewState> = _viewStatePermissions


      //precisa criar outro LiveData?
      // o que preciso fazer pra colocar em Rx(n vai precisar de rx)
      //vou ter que criar algum  state?

      //quando abrir o app eu vou validar a permissao
      //tirar o init, colocar no onviewCreate
      init {
      //getNearbyStores()
      //aqui eu vou chamar a permissao
      }


      private fun getNearbyStores() {
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




