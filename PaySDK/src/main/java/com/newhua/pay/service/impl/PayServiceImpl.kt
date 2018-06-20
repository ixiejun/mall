package com.newhua.pay.service.impl

import com.newhua.mall.base.ext.convert
import com.newhua.mall.base.ext.convertBoolean
import com.newhua.pay.data.repository.PayRepository
import com.newhua.pay.service.PayService
import rx.Observable
import javax.inject.Inject

/**
 * 支付业务实现类
 */
class PayServiceImpl @Inject constructor() : PayService {

    @Inject
    lateinit var repository: PayRepository

    //获取支付签名
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return repository.getPaySign(orderId, totalPrice).convert()
    }

    //支付订单,同步订单状态
    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }
}