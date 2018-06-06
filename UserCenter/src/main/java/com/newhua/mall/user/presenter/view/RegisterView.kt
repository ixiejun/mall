package com.newhua.mall.user.presenter.view

import com.newhua.mall.base.presenter.view.BaseView

interface RegisterView: BaseView {
    fun onRegisterResult(result: String)
}