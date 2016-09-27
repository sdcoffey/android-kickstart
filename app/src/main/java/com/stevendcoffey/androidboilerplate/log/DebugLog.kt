package com.stevendcoffey.androidboilerplate.log

import android.util.Log
import com.stevendcoffey.androidboilerplate.BuildConfig

fun i(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i(caller(), message)
    }
}

fun w(message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(caller(), message)
    }
}

fun e(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(caller(), message)
    }
}

private fun caller(): String {
    val stack = Thread.currentThread().stackTrace
    return stack[4].className
}
