package com.prempal.pangolin.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopNewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    private val _state: MutableLiveData<TopNewsViewState> = MutableLiveData()
    val state: LiveData<TopNewsViewState> = _state

    init {
        fetchNewsArticles()
    }

    fun retryClicked() {
        fetchNewsArticles()
    }

    private fun fetchNewsArticles() {
        viewModelScope.launch {
            _state.value = TopNewsViewState.Loading
            val response = repo.fetchNewsArticles()
            _state.value = when (response) {
                is Response.Success -> TopNewsViewState.Success(response.data)
                is Response.Error -> TopNewsViewState.Error(response.error)
            }
        }
    }
}
