package com.prempal.pangolin.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.prempal.pangolin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by prempal on 2020-02-09.
 */
object ApiClient {

    internal lateinit var API_KEY: String

    fun create(baseUrl: String, apiKey: String): ApiService {
        API_KEY = apiKey
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
