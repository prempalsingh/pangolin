package com.prempal.pangolin.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by prempal on 2020-02-09.
 */
@Singleton
class ViewModelProviderFactory<T : ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException()
    }
}
