package com.newhua.message.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.message.data.protocol.Message
import retrofit2.http.POST
import rx.Observable

/**
 * 消息接口
 */
interface MessageApi {

    //获取消息列表
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResponse<MutableList<Message>?>>
}