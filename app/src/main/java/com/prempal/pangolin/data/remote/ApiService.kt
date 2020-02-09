package com.prempal.pangolin.data.remote

import com.prempal.pangolin.data.remote.response.TopHeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
interface ApiService {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = ApiClient.API_KEY
    ): Single<TopHeadlinesResponse>
}
