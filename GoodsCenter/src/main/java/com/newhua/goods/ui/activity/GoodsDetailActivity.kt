package com.newhua.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.newhua.goods.R
import com.newhua.goods.ui.adapter.GoodsDetailVpAdapter
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseActivity
import com.newhua.mall.provider.router.RouterPath
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

        mAddCartBtn.onClick {
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        }
    }
}