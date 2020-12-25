package com.jaad.carservices.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.jaad.carservices.utils.CommonUtils
import com.jaad.carservices.utils.NetworkUtils

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() , MvpView {

    protected abstract val layoutRes: Int
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    override fun onStart() {
        super.onStart()
        setUp()
    }
    protected abstract fun setUp()

    override fun showLoading() {
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (progressDialog.isShowing)
            progressDialog.cancel()
    }

    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(applicationContext)


    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this , message ,Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(this , getString(resId) ,Toast.LENGTH_SHORT).show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    open fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    override fun openActivityOnTokenExpire() {
        TODO("Not yet implemented")
    }

    override fun onError(resId: Int) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String?) {
        TODO("Not yet implemented")
    }

    override fun onErrorCode(code: Int) {
        TODO("Not yet implemented")
    }

}