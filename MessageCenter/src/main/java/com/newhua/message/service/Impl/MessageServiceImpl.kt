package com.newhua.message.service.Impl

import com.newhua.mall.base.ext.convert
import com.newhua.message.data.protocol.Message
import com.newhua.message.data.repository.MessageRepository
import com.newhua.message.service.MessageService
import rx.Observable
import javax.inject.Inject

/**
 * 消息业务接口
 */
class MessageServiceImpl @Inject constructor() : MessageService {

    @Inject
    lateinit var messageRepository: MessageRepository

    //获取消息列表
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return messageRepository.getMessageList().convert()
    }
}