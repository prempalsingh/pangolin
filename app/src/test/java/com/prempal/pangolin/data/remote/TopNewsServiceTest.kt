package com.prempal.pangolin.data.remote

import com.google.common.truth.Truth.assertThat
import com.prempal.pangolin.data.Response
import com.prempal.pangolin.data.remote.api.ApiService
import com.prempal.pangolin.data.remote.model.NewsResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TopNewsServiceTest {

    private lateinit var apiService: ApiService
    private lateinit var topNewsService: TopNewsService

    @Before
    fun setup() {
        apiService = mockk()
        topNewsService = TopNewsService(apiService)
    }

    @Test
    fun fetchNewsArticlesSuccess() {
        val newsResponse = NewsResponse(emptyList(), "", 0)
        coEvery { apiService.getTopNews() } returns newsResponse

        runBlockingTest {
            val response = topNewsService.fetchNewsArticles()
            assertThat(response is Response.Success).isTrue()
            assertThat((response as Response.Success).data).isEqualTo(newsResponse.articles)
        }
    }

    @Test
    fun fetchNewsArticlesFails() {
        val exceptionToThrow = Exception()
        coEvery { apiService.getTopNews() } throws exceptionToThrow

        runBlockingTest {
            val response = topNewsService.fetchNewsArticles()
            assertThat(response is Response.Error).isTrue()
            assertThat((response as Response.Error).error).isEqualTo(exceptionToThrow)
        }
    }
}
