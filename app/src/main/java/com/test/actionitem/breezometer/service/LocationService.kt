package com.test.actionitem.breezometer.service

import android.widget.Toast
import android.location.LocationManager
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import android.app.Service
import android.content.Context
import android.os.IBinder
import timber.log.Timber


class LocationService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented - add here the functions to be bind to the main activity")
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        } else {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (locationGPS != null) {
                val lat = locationGPS.latitude
                val long = locationGPS.longitude
                Timber.d("Your Location: \nLatitude: $lat\nLongitude: $long")
                // TODO: I had no time but here i would save the lat or the long to a local storage or fire it with RX (publishSubject or something) to the viewModel so the api can feth the AQI based on the given long and lat
            } else {
                Timber.e("Unable to find location.")
            }
        }
    }

    companion object {
        const val REQUEST_LOCATION = 1
    }
}