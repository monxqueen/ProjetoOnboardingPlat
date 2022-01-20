package com.example.common.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val JSON_MEDIA_TYPE = "application/json"

class StubRetrofitBuilder {

    private val BASE_URL = "http://127.0.0.1:8080/"

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun buildRetrofit(): Retrofit {
        val contentType = JSON_MEDIA_TYPE.toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(OkHttpClient.Builder().build())
            .build()
    }
}