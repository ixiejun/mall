package com.newhua.mall.base.ui.fragment

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.newhua.mall.base.common.BaseApplication
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.mall.base.injection.component.DaggerActivityComponent
import com.newhua.mall.base.injection.module.ActivityModule
import com.newhua.mall.base.injection.module.LifecycleProviderModule
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.mall.base.widgets.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

abstract class BaseMvpFragment<T:BasePresenter<*>> : BaseFragment(), BaseView {
    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()

        //mLoadingDialog = ProgressLoading.create(context)
    }

    /*
        Dagger注册
     */
    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity as FragmentActivity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    /*
        错误信息提示，默认实现
     */
    override fun onError(text:String) {
        toast(text)
    }
}