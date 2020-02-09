package com.prempal.pangolin.data

import com.prempal.pangolin.data.local.NewsDatabase
import com.prempal.pangolin.data.remote.ApiService
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by prempal on 2020-02-09.
 */
class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: NewsDatabase
) {

    fun getArticles(): Flowable<List<Article>> = database.articleDao().getAllArticles()

    fun fetchArticles(page: Int) = apiService.getTopHeadlines(page)

    fun deleteAllArticles() = database.articleDao().deleteAllArticles()

    fun insertArticles(articles: List<Article>) = database.articleDao().insertArticles(articles)

}
