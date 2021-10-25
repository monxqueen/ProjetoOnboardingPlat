package com.example.store.data.base

import com.example.store.data.remote.StoresRemoteSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "http://0.0.0.0:8080/store"

object Network {

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun createService(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)

        val contentType = MediaType.get(JSON_MEDIA_TYPE)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(okHttpClient.build())
            .build()
    }

    fun getRemoteSource(): StoresRemoteSource {
        val api = createService()
        return api.create(StoresRemoteSource::class.java)
    }
}