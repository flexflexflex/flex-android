package uz.sesh.flex.flex

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity


inline fun <reified T : ViewModel> getViewModel(fragmentActivity: FragmentActivity): T {
    return ViewModelProviders.of(fragmentActivity)[T::class.java]
}