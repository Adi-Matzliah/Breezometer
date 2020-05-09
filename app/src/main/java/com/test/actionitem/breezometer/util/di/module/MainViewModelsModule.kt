package com.test.actionitem.breezometer.util.di.module

import androidx.lifecycle.ViewModel
import com.test.actionitem.breezometer.util.di.key.ViewModelKey
import com.test.actionitem.breezometer.viewmodel.MainViewModel
import com.trax.storeassistant.util.di.scope.MainScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
        abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel
}