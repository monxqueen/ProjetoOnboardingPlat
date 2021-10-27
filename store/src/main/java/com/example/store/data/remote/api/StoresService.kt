package com.example.store.data.remote.api

import com.example.store.data.remote.model.StoresResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface StoresService {

    @GET("store/list")
    fun getStoresList(): Observable<List<StoresResponse>>
}