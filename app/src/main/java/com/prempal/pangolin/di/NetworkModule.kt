package com.prempal.pangolin.di

import android.content.Context
import com.prempal.pangolin.data.remote.api.ApiClient
import com.prempal.pangolin.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(@ApplicationContext context: Context): ApiService {
        return ApiClient.create(context)
    }
}
