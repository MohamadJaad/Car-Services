package com.jaad.carservices.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<T>(application: Application) : ViewModel() {

    open lateinit var mNavigator: WeakReference<T>

    open fun getNavigator(): T? {
        return mNavigator.get()
    }

    open fun setNavigator(navigator: T) {
        mNavigator = WeakReference<T>(navigator)
    }


    override fun onCleared() {
        super.onCleared()
    }


}