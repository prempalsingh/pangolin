package com.prempal.pangolin

import android.app.Application
import com.prempal.pangolin.di.component.ApplicationComponent
import com.prempal.pangolin.di.component.DaggerApplicationComponent
import com.prempal.pangolin.di.module.ApplicationModule

/**
 * Created by prempal on 2020-02-09.
 */
class PangolinApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }

}
