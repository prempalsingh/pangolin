package com.prempal.pangolin.di.module

import com.prempal.pangolin.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
