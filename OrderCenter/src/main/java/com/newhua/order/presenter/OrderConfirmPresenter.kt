package com.newhua.order.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.order.data.protocol.Order
import com.newhua.order.presenter.view.OrderConfirmView
import com.newhua.order.service.OrderService
import javax.inject.Inject

/**
 * 订单确认页Presenter
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    //根据id获取订单
    fun getOrderById(orderId: Int) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId).execute(object: BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)
    }

    //提交订单
    fun submitOrder(order: Order) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).execute(object: BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        },lifecycleProvider)
    }
}