package com.newhua.order.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.order.data.protocol.Order
import com.newhua.order.presenter.view.OrderDetailView
import com.newhua.order.service.OrderService
import javax.inject.Inject

/**
 * 订单详情页Presenter
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderService: OrderService

    //根据id查询订单
    fun getOrderById(orderId: Int) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()

        orderService.getOrderById(orderId).execute(object : BaseSubscriber<Order>(mView){
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)
    }
}