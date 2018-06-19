package com.newhua.order.presenter

import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.order.data.protocol.ShipAddress
import com.newhua.order.presenter.view.ShipAddressView
import com.newhua.order.service.ShipAddressService
import javax.inject.Inject

/**
 * 收货人列表Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    //获取收货人列表
    fun getShipAddressList() {
        if(!checkNetWork()) {
            return
        }

        mView.showLoading()
        shipAddressService.getShipAddressList().execute(object: BaseSubscriber<MutableList<ShipAddress>?>(mView){
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressResult(t)
            }
        }, lifecycleProvider)
    }

    //设置默认收货人信息
    fun setDefaulShipAddress(address: ShipAddress) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(object: BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, lifecycleProvider)
    }

    //删除收货人信息
    fun deleteShipAddress(id: Int) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id).execute(object: BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onDeleteResult(t)
            }
        }, lifecycleProvider)
    }
}