package com.prempal.pangolin.di.module

import com.prempal.pangolin.BuildConfig
import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.data.remote.ApiClient
import com.prempal.pangolin.data.remote.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Module
class ApplicationModule(private val application: PangolinApplication) {

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        ApiClient.create("https://newsapi.org/v2/", BuildConfig.ApiKey)
}
