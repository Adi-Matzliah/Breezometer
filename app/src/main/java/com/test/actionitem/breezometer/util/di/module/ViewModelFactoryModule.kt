package com.test.actionitem.breezometer.util.di.module

import androidx.lifecycle.ViewModelProvider
import com.test.actionitem.breezometer.util.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
