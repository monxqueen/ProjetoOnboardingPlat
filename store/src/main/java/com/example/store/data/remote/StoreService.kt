package com.example.store.data.remote

import com.example.store.data.remote.model.StoreResponse
import io.reactivex.Single
import retrofit2.http.GET

interface StoreService {

    @GET("list")
    fun getStoresList(): Single<List<StoreResponse>>
}