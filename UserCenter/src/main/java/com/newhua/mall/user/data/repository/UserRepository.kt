package com.newhua.mall.user.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.mall.user.data.api.UserApi
import com.newhua.mall.user.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun register(mobile: String, password: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, password, verifyCode))
    }
}