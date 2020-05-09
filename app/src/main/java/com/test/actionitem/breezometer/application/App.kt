package com.test.actionitem.breezometer.application


import android.app.Service
import androidx.lifecycle.LifecycleObserver
import com.test.actionitem.breezometer.BuildConfig
import com.test.actionitem.breezometer.util.di.component.AppComponent
import com.test.actionitem.breezometer.util.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasServiceInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication(), LifecycleObserver, HasServiceInjector {

    /*@Inject
    lateinit var pushNotificationManager: PushNotificationManager*/

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    init {
        initDebug()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        daggerAppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return daggerAppComponent
    }

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun initDebug() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var daggerAppComponent: AppComponent
    }
}