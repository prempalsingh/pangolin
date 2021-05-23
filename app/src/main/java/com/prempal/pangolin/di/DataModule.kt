package com.prempal.pangolin.di

import com.prempal.pangolin.data.NewsRepository
import com.prempal.pangolin.data.remote.TopNewsService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNewsRepository(topNewsService: TopNewsService): NewsRepository
}
