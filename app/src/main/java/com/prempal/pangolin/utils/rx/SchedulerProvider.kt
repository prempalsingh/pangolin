package com.prempal.pangolin.utils.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
