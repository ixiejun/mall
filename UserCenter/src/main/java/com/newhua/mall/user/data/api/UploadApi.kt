package com.newhua.mall.user.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import retrofit2.http.POST
import rx.Observable

interface UploadApi {
    @POST("/common/getUploadToken")
    fun getUploadToken(): Observable<BaseResponse<String>>
}