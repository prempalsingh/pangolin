package com.prempal.pangolin.data.remote.response

import com.google.gson.annotations.SerializedName
import com.prempal.pangolin.data.Article

/**
 * Created by prempal on 2020-02-09.
 */
data class TopHeadlinesResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
) {
    fun isSuccess() = status == "ok"
}
