package com.test.actionitem.breezometer.util

import android.net.NetworkInfo
import android.telephony.TelephonyManager
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val networkInfo: NetworkInfo?, private val telephonyManager: TelephonyManager?) {

    fun isNetworkAvailable() : Boolean = networkInfo?.isConnected ?: false

    fun getActiveNetworkName() : String = getConnectionType().connectionName

    fun getConnectionType() : ConnectionType =
        when (networkInfo) {
            null -> ConnectionType.OFFLINE
            else -> ConnectionType.fromValue(networkInfo.type)
        }

    fun getNetworkOperatorName(): String = telephonyManager?.networkOperatorName?: "unknown"
}