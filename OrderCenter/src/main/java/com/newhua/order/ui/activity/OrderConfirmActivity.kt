package com.newhua.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseActivity
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.mall.provider.common.ProviderConstant
import com.newhua.mall.provider.router.RouterPath
import com.newhua.order.R
import com.newhua.order.data.protocol.Order
import com.newhua.order.injection.component.DaggerOrderComponent
import com.newhua.order.injection.module.OrderModule
import com.newhua.order.presenter.OrderConfirmPresenter
import com.newhua.order.presenter.view.OrderConfirmView
import com.newhua.order.ui.adapter.OrderGoodsAdapter
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

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
            //
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

    private fun initObserve() {

    }

    //加载数据
    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mAdapter.setData(result.orderGoodsList)
    }

    override fun onSubmitOrderResult(result: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}