package com.prempal.pangolin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prempal.pangolin.data.Article
import io.reactivex.Flowable

/**
 * Created by prempal on 2020-02-09.
 */
@Dao
interface ArticleDao {

    @Insert
    fun insertArticles(articles: List<Article>): List<Long>

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flowable<List<Article>>

    @Query("DELETE FROM articles")
    fun deleteAllArticles()
}
