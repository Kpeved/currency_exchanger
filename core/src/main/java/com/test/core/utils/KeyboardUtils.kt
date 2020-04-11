package com.test.core.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


object KeyboardUtils {

    fun showKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.let {
            imm.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}