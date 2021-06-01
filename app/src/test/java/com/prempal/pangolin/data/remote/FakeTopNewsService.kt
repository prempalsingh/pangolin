package com.prempal.pangolin.data.remote

import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.remote.model.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeTopNewsService : NewsRepository {

    private lateinit var continuation: Continuation<Result<List<Article>>>

    override suspend fun fetchNewsArticles(): Result<List<Article>> {
        return suspendCoroutine {
            continuation = it
        }
    }

    fun emitArticles(articles: List<Article>) {
        val result = Result.success(articles)
        continuation.resume(result)
    }

    fun emitError(error: Throwable) {
        val result = Result.failure<List<Article>>(error)
        continuation.resume(result)
    }
}
