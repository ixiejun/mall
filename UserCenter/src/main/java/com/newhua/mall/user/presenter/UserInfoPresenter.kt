package com.newhua.mall.user.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.mall.user.presenter.view.UserInfoView
import com.newhua.mall.user.service.UploadService
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if(!checkNetWork()) {
            return
        }

        mView.showLoading()

        uploadService.getUploadToken().execute(object: BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }


}