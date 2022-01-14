package com.example.data.data.remote

import com.example.data.data.remote.model.StoreDataSourceResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface StoreService {

    @GET("store/list")
    fun getStoresList(): Single<List<StoreDataSourceResponse>>
}