package com.jaad.carservices.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jaad.carservices.utils.CommonUtils
import com.jaad.carservices.utils.ViewModelProviderFactory

abstract class BaseFragment<VM : BaseViewModel<MvpView>> : Fragment(),
    MvpView {

    protected lateinit var mActivity: BaseActivity<VM>
    lateinit var mProgressDialog: ProgressDialog
    private lateinit var factory: ViewModelProviderFactory

    protected val viewModel: VM by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(getViewModelClass())
    }

    protected abstract fun getViewModelClass(): Class<VM>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity<VM>
        factory = ViewModelProviderFactory(mActivity.application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun showLoading() {
        mProgressDialog = CommonUtils.showLoadingDialog(this.context)
    }

    override fun hideLoading() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.cancel()
        }
    }

    override fun onError(message: String?) {
        if (mActivity != null) {
            mActivity.onError(message)
        }
    }

    override fun onErrorCode(code: Int) {}

    override fun onError(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity.onError(resId)
        }
    }

    override fun showMessage(message: String?) {
        if (mActivity != null) {
            mActivity.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity.showMessage(resId)
        }
    }

    override val isNetworkConnected: Boolean
        get() = mActivity.isNetworkConnected


    override fun onDetach() {
        //        mCompositeDisposable.dispose();
        super.onDetach()
    }

    override fun hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard()
        }
    }

    open fun getBaseActivity(): BaseActivity<VM>? {
        return mActivity
    }

    protected abstract fun setUp(view: View?)

    override fun openActivityOnTokenExpire() {
        TODO("Not yet implemented")
    }

}