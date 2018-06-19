package com.newhua.order.service.Impl

import com.newhua.mall.base.ext.convert
import com.newhua.mall.base.ext.convertBoolean
import com.newhua.order.data.protocol.ShipAddress
import com.newhua.order.data.repository.ShipAddressRepository
import com.newhua.order.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

/*
    收货人信息 业务实现类
 */
class ShipAddressServiceImpl @Inject constructor(): ShipAddressService {

    @Inject
    lateinit var repository: ShipAddressRepository

    /*
        添加收货人信息
     */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName,shipUserMobile,shipAddress).convertBoolean()

    }

    /*
        获取收货人信息列表
     */
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }

    /*
        修改收货人信息
     */
    override fun editShipAddress(address: ShipAddress): Observable<Boolean> {
        return  repository.editShipAddress(address).convertBoolean()
    }

    /*
        删除收货人信息
     */
    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }
}