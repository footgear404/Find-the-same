package com.semenchuk.junior.test.work.findthesameones.presentation.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract fun initBinding(inflater: LayoutInflater): B?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = initBinding(inflater)
        return binding.root
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun navigate(id: Int) {
        findNavController().navigate(id)
    }

    protected fun Fragment.toast(@StringRes stringRes: Int) {
        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }

    protected fun Fragment.toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun Fragment.snack(@StringRes stringRes: Int, view: View) {
        Snackbar.make(view, stringRes, Snackbar.LENGTH_SHORT).show()
    }

    protected fun Fragment.snack(message: String, view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun Fragment.showSystemUI(view: View) {
        val window = requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, view.rootView).show(
            WindowInsetsCompat.Type.systemBars()
        )
    }

    protected fun Fragment.hideSystemUI(view: View) {
        val window = requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, view.rootView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    protected fun Fragment.hideActionBar() {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    protected fun Fragment.setFullScreenMode(view: View) {
        hideSystemUI(view)
    }

    protected fun Fragment.getDrawable(drawable: Int): Drawable? {
        return AppCompatResources.getDrawable(
            requireContext(), drawable
        )
    }

    protected fun <MP : MenuProvider> Fragment.setMenu(provider: MP) {
        with(requireActivity()) {
            (this as MenuHost).addMenuProvider(
                provider, viewLifecycleOwner, Lifecycle.State.RESUMED
            )
        }
    }

    protected fun <I : Any?> flowObserver(flow: Flow<I>?, action: suspend (it: I) -> Unit) =
        viewLifecycleOwner.lifecycleScope.launch {
            flow?.collect {
                action(it)
            }
        }
}