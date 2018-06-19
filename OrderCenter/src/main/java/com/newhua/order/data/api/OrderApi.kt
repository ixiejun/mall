package com.newhua.order.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.order.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    订单 接口
 */
interface OrderApi {

    /*
        取消订单
     */
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderReq): Observable<BaseResponse<String>>

    /*
        确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderReq): Observable<BaseResponse<String>>

    /*
        根据ID获取订单
     */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdReq): Observable<BaseResponse<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListReq): Observable<BaseResponse<MutableList<Order>?>>

    /*
        提交订单
     */
    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderReq): Observable<BaseResponse<String>>

}