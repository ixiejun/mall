package com.newhua.order.presenter.view

import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.order.data.protocol.Order

interface OrderConfirmView : BaseView {
    //获取订单回调
    fun onGetOrderByIdResult(result: Order)

    //提交订单回调
    fun onSubmitOrderResult(result: Boolean)
}