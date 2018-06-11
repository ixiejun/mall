package com.newhua.mall.user.presenter.view

import com.newhua.mall.base.presenter.view.BaseView

interface UserInfoView : BaseView {
    fun onUserInfoResult(result: String)
}