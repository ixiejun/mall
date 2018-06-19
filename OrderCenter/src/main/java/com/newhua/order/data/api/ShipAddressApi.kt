package com.newhua.order.data.api

import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.order.data.protocol.AddShipAddressReq
import com.newhua.order.data.protocol.DeleteShipAddressReq
import com.newhua.order.data.protocol.EditShipAddressReq
import com.newhua.order.data.protocol.ShipAddress
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    地址管理 接口
 */
interface ShipAddressApi {

    /*
        添加收货地址
     */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResponse<String>>

    /*
        删除收货地址
     */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResponse<String>>

    /*
        修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressReq): Observable<BaseResponse<String>>

    /*
        查询收货地址列表
     */
    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>>

}