package com.newhua.message.injection.module

import com.newhua.message.service.Impl.MessageServiceImpl
import com.newhua.message.service.MessageService
import dagger.Module
import dagger.Provides

/**
 * 消息模块业务注入
 */
@Module
class MessageModule {

    @Provides
    fun provideMessageService(messageService: MessageServiceImpl): MessageService {
        return messageService
    }
}