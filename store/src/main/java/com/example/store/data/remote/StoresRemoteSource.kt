package com.example.store.data.remote

import com.example.store.data.model.StoreResponse
import io.reactivex.Single
import retrofit2.http.GET

interface StoresRemoteSource {

    @GET("/list")
    fun getStoresList(): Single<List<StoreResponse>>

}