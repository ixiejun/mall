package com.newhua.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.newhua.goods.R
import com.newhua.goods.ui.adapter.GoodsDetailVpAdapter
import com.newhua.mall.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_goods_detail.*

class GoodsDetailActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
    }
}