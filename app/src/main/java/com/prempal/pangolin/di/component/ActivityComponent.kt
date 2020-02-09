package com.prempal.pangolin.di.component

import com.prempal.pangolin.di.ActivityScope
import com.prempal.pangolin.di.module.ActivityModule
import com.prempal.pangolin.ui.MainActivity
import dagger.Component

/**
 * Created by prempal on 2020-02-09.
 */
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}
