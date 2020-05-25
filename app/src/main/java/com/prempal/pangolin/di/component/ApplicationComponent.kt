package com.prempal.pangolin.di.component

import com.prempal.pangolin.PangolinApplication
import com.prempal.pangolin.di.module.ActivityBuilder
import com.prempal.pangolin.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
@Component(modules = [ApplicationModule::class, AndroidInjectionModule::class, ActivityBuilder::class])
interface ApplicationComponent {

    fun inject(application: PangolinApplication)
}
