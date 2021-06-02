package com.prempal.pangolin.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.remote.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    val articles: LiveData<PagingData<Article>> =
        repo.fetchNewsArticles().cachedIn(viewModelScope).asLiveData()
}
