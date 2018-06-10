package com.newhua.mall.user.presenter.view

import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.mall.user.data.protocol.UserInfo

/*
    用户登录 视图回调
 */
interface LoginView : BaseView {

    fun onLoginResult(result: UserInfo)
}