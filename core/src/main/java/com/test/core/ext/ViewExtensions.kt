package com.test.core.ext

import android.view.View

fun View.show() {
    alpha = 1f
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}