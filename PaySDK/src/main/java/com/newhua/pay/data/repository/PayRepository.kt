package com.newhua.pay.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.pay.data.api.PayApi
import com.newhua.pay.data.protocol.GetPaySignReq
import com.newhua.pay.data.protocol.PayOrderReq
import rx.Observable
import javax.inject.Inject

/*
   支付数据层
 */
class PayRepository @Inject constructor() {

    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }


}