package com.prempal.pangolin.data

import com.prempal.pangolin.data.remote.model.Article

interface NewsRepository {
    suspend fun fetchNewsArticles(): Response<List<Article>>
}