package com.prempal.pangolin.topnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prempal.pangolin.data.NewsRepository
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
            val result = repo.fetchNewsArticles()
            result.fold({
                _state.value = TopNewsViewState.Success(it)
            }, {
                _state.value = TopNewsViewState.Error(it)
            })
        }
    }
}
