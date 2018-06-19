package com.newhua.order.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.order.data.protocol.ShipAddress
import com.newhua.order.presenter.view.EditShipAddressView
import com.newhua.order.service.ShipAddressService
import javax.inject.Inject

/**
 * 编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    //添加收货人信息
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if(!checkNetWork()) {
            return
        }

        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName,
                shipUserMobile, shipAddress).execute(object : BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onAddShipAddressResult(t)
            }
        }, lifecycleProvider)
    }

    //修改收货人信息
    fun editShipAdress(address: ShipAddress) {
        if(!checkNetWork()) {
            return
        }

        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(object : BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        }, lifecycleProvider)
    }
}