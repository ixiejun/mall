package com.newhua.mall.user.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.mall.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResponse<String>>
}


