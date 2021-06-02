package com.prempal.pangolin.data

import androidx.paging.PagingData
import com.prempal.pangolin.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchNewsArticles(): Flow<PagingData<Article>>
}
