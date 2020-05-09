package com.test.actionitem.breezometer.util.di.module

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.getSystemService
import com.test.actionitem.breezometer.util.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class PushNotificationModule {

    @Provides
    @AppScope
    internal fun provideNotificationManager(context: Context) = context.getSystemService<NotificationManager>()

/*    @Provides
    @AppScope
    internal fun providePushNotificationManager(userRepo: UserRepository, notificationManager: NotificationManager?*//*, graphQLRepo: GraphQLRepository*//*) = PushNotificationManager(userRepo, notificationManager*//*, graphQLRepo*//*)*/
}