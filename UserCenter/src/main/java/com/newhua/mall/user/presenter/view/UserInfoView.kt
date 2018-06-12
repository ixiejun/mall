package com.newhua.mall.user.presenter.view

import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.mall.user.data.protocol.UserInfo

interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)

    fun onUserInfoResult(result: String)

    fun OnEditUserResult(result: UserInfo)
}