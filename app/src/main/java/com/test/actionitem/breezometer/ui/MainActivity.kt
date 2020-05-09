package com.test.actionitem.breezometer.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.test.actionitem.breezometer.BR
import com.test.actionitem.breezometer.databinding.MainActivityBinding
import com.test.actionitem.breezometer.util.di.ViewModelProviderFactory
import com.test.actionitem.breezometer.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import android.graphics.Color
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.observe
import com.test.actionitem.breezometer.service.LocationService
import android.location.LocationManager
import android.content.Context
import com.test.actionitem.breezometer.R


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get<MainViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initLocationPermission()
        setupSpeedometer()
        observeFields()
    }

    private fun initLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LocationService.REQUEST_LOCATION)
    }

    private fun showGpsPopUp() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes"
        ) { dialog, which -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton("No"
            ) { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun setupSpeedometer() {
        with(binding.speedometer) {
            // configure value range and ticks
            maxSpeed = 300.toDouble()
            majorTickStep = 30.toDouble()
            minorTicks = 2

            // Configure value range colors
            addColoredRange(30.toDouble(), 140.toDouble(), Color.GREEN)
            addColoredRange(140.toDouble(), 180.toDouble(), Color.YELLOW)
            addColoredRange(180.toDouble(), 400.toDouble(), Color.RED)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    private fun observeFields() {
        viewModel.airQuality.observe(this) {
            // Add label converter
            binding.speedometer.setLabelConverter { airQuality, _ -> airQuality.toString() }
        }

        viewModel.isRefreshBtnIsClicked.observe(this) {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showGpsPopUp()
            } else {
                //getLocation() call service
            }
        }
    }
}
