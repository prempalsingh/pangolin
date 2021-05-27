package com.prempal.pangolin.data.remote.api

import com.prempal.pangolin.BuildConfig
import com.prempal.pangolin.data.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): NewsResponse
}
