package com.newhua.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ext.setVisible
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.mall.base.utils.YuanFenConverter
import com.newhua.mall.provider.common.ProviderConstant
import com.newhua.mall.provider.router.RouterPath
import com.newhua.order.R
import com.newhua.order.data.protocol.Order
import com.newhua.order.event.SelectAddressEvent
import com.newhua.order.injection.component.DaggerOrderComponent
import com.newhua.order.injection.module.OrderModule
import com.newhua.order.presenter.OrderConfirmPresenter
import com.newhua.order.presenter.view.OrderConfirmView
import com.newhua.order.ui.adapter.OrderGoodsAdapter
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {


    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0

    private lateinit var mAdapter: OrderGoodsAdapter
    private var mCurrentOrder: Order? = null

    //Daggger注册
    override fun injectComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(activityComponent)
                .orderModule(OrderModule())
                .build().inject(this)

        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
        loadData()
    }

    //初始化视图
    private fun initView() {
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }

        //订单中商品列表
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    //初始化选择收货人事件监听
    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe() {
                    t: SelectAddressEvent ->
                    run {
                        mCurrentOrder?.let {
                            it.shipAddress = t.address
                        }
                        updateAddressView()
                    }
                }.registerInBus(this)
    }

    //根据是否有收货人信息更新视图
    private fun updateAddressView() {
        mCurrentOrder?.let {
            if(it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "    " + it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    //加载数据
    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    //获取订单回调
    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计: ${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    //取消事件监听
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    //提交订单回调
    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")
        ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                .withInt(ProviderConstant.KEY_ORDER_ID, mCurrentOrder!!.id)
                .withLong(ProviderConstant.KEY_ORDER_PRICE, mCurrentOrder!!.totalPrice)
                .navigation()

        finish()
    }
}