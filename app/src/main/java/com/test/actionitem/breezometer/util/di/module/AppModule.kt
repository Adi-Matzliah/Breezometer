package com.test.actionitem.breezometer.util.di.module

import android.content.Context
import com.test.actionitem.breezometer.util.ResourcesLoader
import com.test.actionitem.breezometer.util.di.scope.AppScope
import dagger.Module
import dagger.Provides


@Module(
    includes = [ContextModule::class]
)
class AppModule {

    @AppScope
    @Provides
    fun provideResourcesLoader(context: Context) = ResourcesLoader(context)
}