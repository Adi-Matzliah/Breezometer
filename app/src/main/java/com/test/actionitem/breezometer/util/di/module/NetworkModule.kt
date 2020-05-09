package com.test.actionitem.breezometer.util.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import androidx.core.content.getSystemService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.actionitem.breezometer.R
import com.test.actionitem.breezometer.network.BreezometerApi
import com.test.actionitem.breezometer.util.NetworkUtils
import com.test.actionitem.breezometer.util.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @AppScope
    internal fun provideHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @AppScope
    internal fun provideHttpLoginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @AppScope
    internal fun provideOkHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache/*null*/)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            /*.authenticator(authenticator)*/
            .build()

    @Provides
    @AppScope
    internal fun provideRetrofit(context: Context, gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @AppScope
    internal fun provideGson(gsonBuilder: GsonBuilder): Gson {
        val gsonBuilder = gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @AppScope
    internal fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @AppScope
    internal fun provideLogglyApi(retrofit: Retrofit): BreezometerApi = retrofit.create(BreezometerApi::class.java)

    @Provides
    @AppScope
    internal fun provideNetworkUtils(networkInfo: NetworkInfo?, telephonyManager: TelephonyManager?): NetworkUtils = NetworkUtils(networkInfo, telephonyManager)

    @Provides
    @AppScope
    internal fun provideNetworkInfo(context: Context): NetworkInfo? {
        val connectivityManager = context.getSystemService<ConnectivityManager>()
        return connectivityManager?.activeNetworkInfo
    }
}