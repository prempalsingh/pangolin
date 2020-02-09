package com.prempal.pangolin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prempal.pangolin.data.Article
import com.prempal.pangolin.data.ArticleRepository
import com.prempal.pangolin.utils.Event
import com.prempal.pangolin.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

/**
 * Created by prempal on 2020-02-09.
 */
class MainViewModel(
    private val articleRepository: ArticleRepository,
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val paginator: PublishProcessor<Int>
) : ViewModel() {

    private val _items = MutableLiveData<List<Article>>()
    val items: LiveData<List<Article>> = _items

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _openArticleEvent = MutableLiveData<Event<String>>()
    val openArticleEvent: LiveData<Event<String>> = _openArticleEvent

    private var page = 1

    init {
        compositeDisposable.add(articleRepository.getArticles()
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                if (it.isNotEmpty()) _items.postValue(it)
            }, {
                it.printStackTrace()
            })
        )

        compositeDisposable.add(
            paginator
                .onBackpressureDrop()
                .doOnNext {
                    _loading.postValue(true)
                }
                .concatMapSingle {
                    return@concatMapSingle articleRepository
                        .fetchArticles(page)
                        .flatMap {
                            if (it.status == "ok") {
                                if (page == 1) {
                                    articleRepository.deleteAllArticles()
                                }
                                articleRepository.insertArticles(it.articles)
                                Single.just(it.totalResults)
                            } else Single.error(Exception())
                        }
                        .subscribeOn(schedulerProvider.io())
                        .doOnError {
                            it.printStackTrace()
                            _loading.postValue(false)
                        }
                }
                .subscribe(
                    {
                        _loading.postValue(false)
                    },
                    {
                        _loading.postValue(false)
                    }
                )
        )

        fetchArticles()
    }

    fun onHeadlineClick(position: Int) {
        _items.value?.get(position)?.url?.takeIf { it.isNotBlank() }?.let {
            _openArticleEvent.postValue(Event(it))
        }
    }

    private fun fetchArticles() {
        paginator.onNext(page)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
