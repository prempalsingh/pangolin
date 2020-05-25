package com.prempal.pangolin

import android.app.Activity
import android.app.Application
import com.prempal.pangolin.di.component.ApplicationComponent
import com.prempal.pangolin.di.component.DaggerApplicationComponent
import com.prempal.pangolin.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 * Created by prempal on 2020-02-09.
 */
class PangolinApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    private fun inject() {
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }

}
