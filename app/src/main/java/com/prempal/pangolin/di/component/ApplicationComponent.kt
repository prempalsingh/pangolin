package com.prempal.pangolin.di.component

import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.data.local.NewsDatabase
import com.prempal.pangolin.data.remote.ApiService
import com.prempal.pangolin.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: PangolinApplication)

    fun getNewsDatabase(): NewsDatabase

    fun getApiService(): ApiService
}
