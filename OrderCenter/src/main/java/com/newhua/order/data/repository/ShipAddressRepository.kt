package com.newhua.order.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.order.data.api.ShipAddressApi
import com.newhua.order.data.protocol.AddShipAddressReq
import com.newhua.order.data.protocol.DeleteShipAddressReq
import com.newhua.order.data.protocol.EditShipAddressReq
import com.newhua.order.data.protocol.ShipAddress
import rx.Observable
import javax.inject.Inject

/*
   收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).addShipAddress(AddShipAddressReq(shipUserName,shipUserMobile,shipAddress))
    }

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).deleteShipAddress(DeleteShipAddressReq(id))
    }

    /*
        修改收货地址
     */
    fun editShipAddress(address: ShipAddress): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).editShipAddress(EditShipAddressReq(address.id,address.shipUserName,address.shipUserMobile,address.shipAddress,address.shipIsDefault))
    }

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).getShipAddressList()
    }

}