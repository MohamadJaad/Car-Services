package com.jaad.carservices.ui.base

import androidx.annotation.StringRes

interface MvpView {
    fun showLoading()
    fun hideLoading()
    fun openActivityOnTokenExpire()
    fun onError(@StringRes resId: Int)
    fun onError(message: String?)
    fun onErrorCode(code: Int)
    fun showMessage(message: String?)
    fun showMessage(@StringRes resId: Int)
    val isNetworkConnected: Boolean
    fun hideKeyboard()
}