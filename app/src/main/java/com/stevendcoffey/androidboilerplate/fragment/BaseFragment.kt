package com.stevendcoffey.androidboilerplate.fragment

import android.app.Fragment
import android.os.Bundle
import com.stevendcoffey.androidboilerplate.annotations.findViewsForObject
import com.stevendcoffey.androidboilerplate.annotations.restoreObjectInstanceState
import com.stevendcoffey.androidboilerplate.annotations.saveObjectInstanceState

open class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreObjectInstanceState(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveObjectInstanceState(this, outState)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViewsForObject(this, view)
    }

    override fun onPause() {
        super.onPause()
    }
}

