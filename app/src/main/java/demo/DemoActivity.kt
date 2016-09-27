package demo

import android.os.Bundle
import com.stevendcoffey.androidboilerplate.R
import com.stevendcoffey.androidboilerplate.base.BaseActivity
import com.stevendcoffey.androidboilerplate.log.i

class DemoActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            i("Starting up!")
            val testFragment = DemoFragment.newInstance()
            fragmentManager.beginTransaction().replace(R.id.container, testFragment).commit()
        }
    }
}

