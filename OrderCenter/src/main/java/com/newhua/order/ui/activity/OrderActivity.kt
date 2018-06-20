package com.newhua.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.newhua.mall.base.ui.activity.BaseActivity
import com.newhua.order.R
import com.newhua.order.common.OrderConstant
import com.newhua.order.common.OrderStatus
import com.newhua.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * 订单Activity
 * 主要包括不同订单状态的Fragment
 */
class OrderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    //初始化视图
    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager,this)
        mOrderTab.setupWithViewPager(mOrderVp)

        //根据订单状态设置当前页面
        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }
}