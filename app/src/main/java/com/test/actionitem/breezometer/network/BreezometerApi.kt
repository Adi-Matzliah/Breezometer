package com.test.actionitem.breezometer.network

import com.test.actionitem.breezometer.network.response.AQIResponse
import io.reactivex.Single
import retrofit2.http.*

interface BreezometerApi {
    @GET("current-conditions/")
    fun getAQI(
        @Query("key") apiToken: String,
        @Query("lon") longitude: String,
        @Query("lat") latitude: String
    ): Single<AQIResponse>
}