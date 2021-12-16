package com.example.data.data.remote

import com.example.data.data.remote.model.Constants
import com.example.data.data.remote.retrofit.RetrofitBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val JSON_MEDIA_TYPE = "application/json"

class RetrofitBuilderImpl : RetrofitBuilder {

    private val LOCAL_IP = Constants.LOCAL_IP.value
    private val BASE_URL = "http://$LOCAL_IP:8080/"

    private fun getLoggingInterceptor(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return httpClient
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    override fun buildRetrofit(): Retrofit {
        val contentType = JSON_MEDIA_TYPE.toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(getLoggingInterceptor().build())
            .build()
    }

//    val retrofitService : StoreService by lazy {
//        buildRetrofit().create(StoreService::class.java)
//    }
}

