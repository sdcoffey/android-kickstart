package com.stevendcoffey.androidboilerplate.fragment

import android.app.Activity
import android.os.Bundle
import com.stevendcoffey.androidboilerplate.annotations.restoreObjectInstanceState
import com.stevendcoffey.androidboilerplate.annotations.saveObjectInstanceState

open abstract class BaseActivity: Activity() {

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        restoreObjectInstanceState(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveObjectInstanceState(this, outState)
    }
}