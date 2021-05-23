package com.prempal.pangolin.topnews

import com.prempal.pangolin.data.remote.model.Article

sealed class TopNewsViewState {
    object Loading : TopNewsViewState()
    data class Success(val articles: List<Article>) : TopNewsViewState()
    data class Error(val error: Throwable) : TopNewsViewState()
}
