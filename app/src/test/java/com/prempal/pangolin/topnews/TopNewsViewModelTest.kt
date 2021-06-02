package com.prempal.pangolin.topnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.prempal.pangolin.data.remote.FakeTopNewsService
import com.prempal.pangolin.data.remote.model.Article
import com.prempal.pangolin.utils.MainCoroutineRule
import com.prempal.pangolin.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TopNewsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeTopNewsService
    private lateinit var viewModel: TopNewsViewModel

    @Before
    fun setup() {
        repository = FakeTopNewsService()
        viewModel = TopNewsViewModel(repository)
    }

    @Test
    fun fetchNewsArticlesSuccess() {
        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Loading)

        val articles = emptyList<Article>()
        repository.emitArticles(articles)

        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Success(articles))
    }

    @Test
    fun fetchNewsArticlesFails() {
        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Loading)

        val exceptionToThrow = Exception()
        repository.emitError(exceptionToThrow)

        assertThat(viewModel.articles.getOrAwaitValue())
            .isEqualTo(
                TopNewsViewState.Error(exceptionToThrow)
            )
    }

    @Test
    fun retryFetchNewsArticlesFailed() {
        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Loading)

        val articles = emptyList<Article>()
        val exceptionToThrow = Exception()

        repository.emitError(exceptionToThrow)
        assertThat(viewModel.articles.getOrAwaitValue())
            .isEqualTo(
                TopNewsViewState.Error(exceptionToThrow)
            )

        viewModel.retryClicked()

        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Loading)

        repository.emitArticles(articles)
        assertThat(viewModel.articles.getOrAwaitValue()).isEqualTo(TopNewsViewState.Success(articles))
    }
}
