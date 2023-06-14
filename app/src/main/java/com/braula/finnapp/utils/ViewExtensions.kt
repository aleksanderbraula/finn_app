package com.braula.finnapp.utils

import android.view.View

infix fun View.visibility(isVisible: Boolean?) {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
        null -> View.INVISIBLE
    }
}