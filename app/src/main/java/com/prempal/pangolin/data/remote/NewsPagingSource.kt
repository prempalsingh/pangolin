package com.prempal.pangolin.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prempal.pangolin.data.remote.api.ApiService
import com.prempal.pangolin.data.remote.model.Article

class NewsPagingSource(private val apiService: ApiService) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val nextPageNumber = params.key ?: 1
        return try {
            // TODO: 20/05/21 check response status
            val response = apiService.getTopNews(pageNumber = nextPageNumber)
            LoadResult.Page(
                response.articles,
                null,
                if (response.totalResults > ((nextPageNumber.minus(1))
                        .times(params.loadSize)).plus(response.articles.size)
                ) nextPageNumber.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
}
