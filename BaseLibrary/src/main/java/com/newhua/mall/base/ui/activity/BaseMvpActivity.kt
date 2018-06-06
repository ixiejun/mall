package com.newhua.mall.base.ui.activity

import android.os.Bundle
import com.newhua.mall.base.common.BaseApplication
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.mall.base.injection.component.DaggerActivityComponent
import com.newhua.mall.base.injection.module.ActivityModule
import com.newhua.mall.base.injection.module.LifecycleProviderModule
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.presenter.view.BaseView
import javax.inject.Inject

abstract class BaseMvpActivity<T:BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
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

    /*
        错误信息提示，默认实现
     */
    override fun onError(text:String) {

    }
}