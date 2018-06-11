package com.newhua.mall.user.presenter

import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.user.presenter.view.UserInfoView
import com.newhua.mall.user.service.UserService
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService
}