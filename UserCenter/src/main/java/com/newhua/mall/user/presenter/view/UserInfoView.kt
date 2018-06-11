package com.newhua.mall.user.presenter.view

import com.newhua.mall.base.presenter.view.BaseView

interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)

    fun onUserInfoResult(result: String)
}