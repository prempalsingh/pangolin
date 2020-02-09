package com.prempal.pangolin.ui

import androidx.lifecycle.ViewModel
import com.prempal.pangolin.data.ArticleRepository
import com.prempal.pangolin.utils.rx.SchedulerProvider
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
