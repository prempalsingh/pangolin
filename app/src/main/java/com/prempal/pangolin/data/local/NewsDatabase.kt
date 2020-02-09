package com.prempal.pangolin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prempal.pangolin.data.Article
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
