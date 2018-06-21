package com.newhua.message.presenter.view

import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.message.data.protocol.Message

/**
 * 消息列表 视图回调
 */
interface MessageView : BaseView {

    //获取消息列表回调
    fun onGetMessageResult(result: MutableList<Message>?)
}