package com.test.actionitem.breezometer.util.di.module

import com.test.actionitem.breezometer.ui.MainActivity
import com.trax.storeassistant.util.di.scope.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}