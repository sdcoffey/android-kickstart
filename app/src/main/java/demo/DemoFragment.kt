package demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import com.stevendcoffey.androidboilerplate.R
import com.stevendcoffey.androidboilerplate.annotations.FindView
import com.stevendcoffey.androidboilerplate.annotations.Retain
import com.stevendcoffey.androidboilerplate.base.BaseFragment

class DemoFragment : BaseFragment(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    companion object {
        fun newInstance(): DemoFragment {
            return DemoFragment()
        }
    }

    @FindView(R.id.main_fragment_text_view) var textView: TextView? = null
    @FindView(R.id.main_fragment_button) var button: Button? = null
    @FindView(R.id.main_fragment_switch) var switch: Switch? = null

    @Retain var mInt: Int = 0
        set(newVal) {
            field = newVal
            button?.text = "$field"
        }

    @Retain var mBool: Boolean = false
        set(newVal) {
            field = newVal
            textView?.text = field.toString()
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        inflater?.let { _inflater ->
            return _inflater.inflate(R.layout.fragment_main, container, false)
        }
        return View(activity)
    }

    override fun onResume() {
        super.onResume()
        textView?.text = mBool.toString()
        button?.text = "$mInt"
        button?.setOnClickListener(this)
        switch?.setOnCheckedChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        button?.setOnClickListener(null)
        switch?.setOnCheckedChangeListener(null)
    }

    override fun onClick(v: View?) {
        if (v == button) {
           mInt++
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        mBool = isChecked
    }
}