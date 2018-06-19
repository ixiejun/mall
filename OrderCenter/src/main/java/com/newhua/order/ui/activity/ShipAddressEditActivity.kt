package com.newhua.order.ui.activity

import android.os.Bundle
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.order.R
import com.newhua.order.common.OrderConstant
import com.newhua.order.data.protocol.ShipAddress
import com.newhua.order.injection.component.DaggerShipAddressComponent
import com.newhua.order.injection.module.ShipAddressModule
import com.newhua.order.presenter.EditShipAddressPresenter
import com.newhua.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * 收货人编辑页面
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    private var mAddress: ShipAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_address)
        initView()
        initData()
    }

    //初始化视图
    private fun initView() {
        mSaveBtn.onClick {
            if(mShipNameEt.text.isNullOrEmpty()) {
                toast("名称不能为空")
                return@onClick
            }

            if(mShipMobileEt.text.isNullOrEmpty()) {
                toast("电话不能为空")
                return@onClick
            }

            if(mShipAddressEt.text.isNullOrEmpty()) {
                toast("地址不能为空")
                return@onClick
            }

            if(mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            } else {
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAdress(mAddress!!)
            }
        }
    }

    //初始化数据
    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    //Dagger注册
    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule()).build().inject(this)

        mPresenter.mView = this
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("修改地址成功")
        finish()
    }
}