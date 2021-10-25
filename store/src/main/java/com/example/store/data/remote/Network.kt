package com.example.store.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val JSON_MEDIA_TYPE = "application/json"
private const val baseUrl = "http://0.0.0.0:8080/"

object Network {

    @Suppress("EXPERIMENTAL_API_USAGE")
    fun getService(): ApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val contentType = MediaType.get(JSON_MEDIA_TYPE)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
