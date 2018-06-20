package com.newhua.order.presenter.view

import com.newhua.mall.base.presenter.view.BaseView
import com.newhua.order.data.protocol.Order

/**
 * 订单详情,视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}