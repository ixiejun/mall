package com.newhua.message.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.message.data.api.MessageApi
import com.newhua.message.data.protocol.Message
import rx.Observable
import javax.inject.Inject

/**
 * 消息数据层
 */
class MessageRepository @Inject constructor() {

    //获取消息列表
    fun getMessageList(): Observable<BaseResponse<MutableList<Message>?>> {
        return RetrofitFactory.instance.create(MessageApi::class.java).getMessageList()
    }
}