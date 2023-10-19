package freelancer.istanbul.movielist.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected open var is_back_refresh: Boolean = false
    val viewBinding get() = _viewBinding

    private lateinit var _viewBinding: T

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun T.onBindView(savedInstanceState: Bundle?)

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::_viewBinding.isInitialized || rootView == null || is_back_refresh) {
            _viewBinding = getBinding(inflater, container)
            _viewBinding.onBindView(savedInstanceState)

            rootView = _viewBinding.root
        }
        return rootView
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

    protected fun helperGoToActivity(activity: Class<*>) {
        Intent(requireContext(), activity).apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    protected fun helperGoToFragment(directions: NavDirections, is_this_delete: Boolean = false) {
        NavHostFragment.findNavController(this.requireParentFragment()).navigate(directions, if (is_this_delete) NavOptions.Builder().setPopUpTo(this.id, true).build() else null)
    }

    protected fun helperBackFragment() {
        helperKlavye(false)
        this.activity?.onBackPressedDispatcher?.onBackPressed()
    }

    protected fun helperToastMessage(message: String?) {
        message?.let { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }
    }

    protected fun helperKlavye(open: Boolean) {
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

        if (open) inputMethodManager?.showSoftInput(requireView(), InputMethodManager.SHOW_IMPLICIT)
        else inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}