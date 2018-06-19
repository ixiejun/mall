package com.newhua.mall.base.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.newhua.mall.base.common.BaseApplication
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.mall.base.injection.component.DaggerActivityComponent
import com.newhua.mall.base.injection.module.ActivityModule
import com.newhua.mall.base.injection.module.LifecycleProviderModule
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.mall.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<T:BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    /*
        Dagger注册
     */
    protected abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
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