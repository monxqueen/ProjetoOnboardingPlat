package com.example.store.data.remote

import com.example.store.data.remote.model.StoresResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("store/list")
    fun getStoresList(): Observable<List<StoresResponse>>
}