package com.test.actionitem.breezometer.data
import com.test.actionitem.breezometer.network.BreezometerApi
import com.test.actionitem.breezometer.network.response.AQIResponse
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: BreezometerApi) {

    fun getAQI(apiToken: String = "e940d808cb934688b1feba3bcb404359",
               longitude: String = "34.887760162353516",
               latitude: String = "32.084041595458984") = api.getAQI(apiToken, longitude, latitude)

}


