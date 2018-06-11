package com.newhua.mall.user.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.mall.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

class UploadRepository @Inject constructor() {
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }
}