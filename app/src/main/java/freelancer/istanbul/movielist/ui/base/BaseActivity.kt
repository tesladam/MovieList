package freelancer.istanbul.movielist.ui.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    abstract fun getBinding(): T

    lateinit var viewBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getBinding()
        setContentView(viewBinding.root)
    }

    protected fun logGoster(log: String) {
        val maxLogSize = 1000
        for (i in 0..log.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > log.length) log.length else end
            Log.v("asdasd", log.substring(start, end))
        }
    }

    protected fun helperToastMessage(message: String?) {
        message?.let { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }

}