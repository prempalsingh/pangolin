package com.prempal.pangolin.di.module

import androidx.room.Room
import com.prempal.pangolin.BuildConfig
import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.data.local.NewsDatabase
import com.prempal.pangolin.data.remote.ApiClient
import com.prempal.pangolin.data.remote.ApiService
import com.prempal.pangolin.utils.rx.RxSchedulerProvider
import com.prempal.pangolin.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Module
class ApplicationModule(private val application: PangolinApplication) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideDatabase(): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, "news-db").build()

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        ApiClient.create("https://newsapi.org/v2/", BuildConfig.ApiKey)
}
