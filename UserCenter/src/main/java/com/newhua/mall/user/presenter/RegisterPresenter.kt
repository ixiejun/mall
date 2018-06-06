package com.newhua.mall.user.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.mall.user.presenter.view.RegisterView
import com.newhua.mall.user.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, password: String, verifyCode: String) {
        /**
         * 业务逻辑
         */
        userService.register(mobile, password, verifyCode)
                .execute(object :BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        if(t)
                            mView.onRegisterResult("注册成功")

                    }
                }, lifecycleProvider)

    }
}