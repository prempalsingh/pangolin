package com.prempal.pangolin.data.remote

import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.Response
import com.prempal.pangolin.data.remote.api.ApiService
import com.prempal.pangolin.data.remote.model.Article
import javax.inject.Inject

class TopNewsService @Inject constructor(
    private val apiService: ApiService
) : NewsRepository {
    override suspend fun fetchNewsArticles(): Response<List<Article>> {
        return try {
            // TODO: 20/05/21 check response status
            val articles = apiService.getTopNews().articles
            Response.Success(articles)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
