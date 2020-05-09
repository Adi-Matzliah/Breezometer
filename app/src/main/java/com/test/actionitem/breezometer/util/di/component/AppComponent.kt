package com.test.actionitem.breezometer.util.di.component

import com.test.actionitem.breezometer.application.App
import com.test.actionitem.breezometer.util.di.module.*
import com.test.actionitem.breezometer.util.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        ServiceBuilderModule::class,
        ContextModule::class,
        NetworkModule::class,
        PushNotificationModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}