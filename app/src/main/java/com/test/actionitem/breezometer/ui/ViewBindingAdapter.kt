package com.test.actionitem.breezometer.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.databinding.BindingAdapter
import timber.log.Timber

@BindingAdapter("view:drawBackgroundColor")
fun View.setViewBackgroundColor(colorString: String?) {
    colorString?.let {
        try {
            background = ColorDrawable(Color.parseColor(colorString))
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
    }
}
