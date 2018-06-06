package com.newhua.mall.base.presenter

import com.newhua.mall.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T:BaseView> {

    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}