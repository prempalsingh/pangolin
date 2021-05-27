package com.prempal.pangolin.data.remote

import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.Response
import com.prempal.pangolin.data.remote.model.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeTopNewsService : NewsRepository {

    private lateinit var continuation: Continuation<Response<List<Article>>>

    override suspend fun fetchNewsArticles(): Response<List<Article>> {
        return suspendCoroutine {
            continuation = it
        }
    }

    fun emitArticles(articles: List<Article>) {
        val response = Response.Success(articles)
        continuation.resume(response)
    }

    fun emitError(error: Throwable) {
        val response = Response.Error<List<Article>>(error)
        continuation.resume(response)
    }
}
