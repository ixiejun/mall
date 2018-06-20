package com.newhua.pay.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.pay.data.protocol.GetPaySignReq
import com.newhua.pay.data.protocol.PayOrderReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    支付 接口
 */
interface PayApi {

    /*
        获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResponse<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResponse<String>>

}