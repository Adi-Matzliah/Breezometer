package com.test.actionitem.breezometer.util.di.module

import android.content.Context
import com.test.actionitem.breezometer.application.App
import com.test.actionitem.breezometer.util.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    @Provides
    @AppScope
    fun provideContext(app: App): Context = app
}