package br.com.customapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object SystemUtil {

    fun hideKeyboard(activity: Activity?) {
        activity?.currentFocus?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}