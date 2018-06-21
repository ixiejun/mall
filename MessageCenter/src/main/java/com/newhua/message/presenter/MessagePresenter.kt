package com.newhua.message.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.message.data.protocol.Message
import com.newhua.message.presenter.view.MessageView
import com.newhua.message.service.MessageService
import javax.inject.Inject

/**
 * 消息列表Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    //获取消息列表
    fun getMessageList() {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()

        messageService.getMessageList().execute(object: BaseSubscriber<MutableList<Message>?>(mView){
            override fun onNext(t: MutableList<Message>?) {
                mView.onGetMessageResult(t)
            }
        }, lifecycleProvider)
    }
}