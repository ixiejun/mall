package com.newhua.mall.user.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.mall.user.presenter.view.ResetPwdView
import com.newhua.mall.user.service.UserService
import javax.inject.Inject

/*
    重置密码Presenter
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService


    /*
        重置密码
     */
    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.resetPwd(mobile, pwd).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                if (t)
                    mView.onResetPwdResult("重置密码成功")
            }
        }, lifecycleProvider)

    }

}