package com.prempal.pangolin.di

import com.prempal.pangolin.data.remote.api.ApiClient
import com.prempal.pangolin.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.create()
    }
}
