package com.prempal.pangolin.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import com.prempal.pangolin.data.ArticleRepository
import com.prempal.pangolin.ui.MainViewModel
import com.prempal.pangolin.utils.ViewModelProviderFactory
import com.prempal.pangolin.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

/**
 * Created by prempal on 2020-02-09.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideMainViewModel(
        articleRepository: ArticleRepository,
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
    ): MainViewModel =
        ViewModelProvider(
            activity,
            ViewModelProviderFactory(MainViewModel::class) {
                MainViewModel(
                    articleRepository,
                    schedulerProvider,
                    compositeDisposable,
                    PublishProcessor.create()
                )
            }).get(MainViewModel::class.java)

    @Provides
    fun provideCustomChromeTabIntent(): CustomTabsIntent = CustomTabsIntent.Builder().build()
}
