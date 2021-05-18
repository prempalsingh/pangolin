package com.prempal.pangolin.api

import com.squareup.moshi.Json

data class NewsResponse(
    // TODO: 18/05/21 articles can be null in case of error?
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)
