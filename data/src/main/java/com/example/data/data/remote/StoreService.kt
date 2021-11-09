package com.example.data.data.remote

import com.example.data.data.remote.model.StoreResponse
import io.reactivex.Single
import retrofit2.http.GET

interface StoreService {

    @GET("store/list")
    fun getStoresList(): Single<List<StoreResponse>>
}