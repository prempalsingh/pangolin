package com.prempal.pangolin.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.remote.api.ApiService
import com.prempal.pangolin.data.remote.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopNewsService @Inject constructor(
    private val apiService: ApiService
) : NewsRepository {
    override fun fetchNewsArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(apiService) }
        ).flow
    }
}
