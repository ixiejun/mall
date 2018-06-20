package com.newhua.pay.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.pay.presenter.view.PayView
import com.newhua.pay.service.PayService
import javax.inject.Inject

/**
 * 支付Presenter
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var service: PayService

    //获取支付签名
    fun getPaySign(orderId: Int, totalPrice: Long) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()

        service.getPaySign(orderId, totalPrice).execute(object: BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetSignResult(t)
            }
        }, lifecycleProvider)
    }

    //订单支付,同步订单状态
    fun payOrder(orderId: Int) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()

        service.payOrder(orderId).execute(object: BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onPayOrderResult(t)
            }
        }, lifecycleProvider)
    }
}