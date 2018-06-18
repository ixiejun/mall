package com.newhua.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.newhua.goods.R
import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.event.AddCartEvent
import com.newhua.goods.event.UpdateCartSizeEvent
import com.newhua.goods.ui.adapter.GoodsDetailVpAdapter
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseActivity
import com.newhua.mall.base.utils.AppPrefsUtils
import com.newhua.mall.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

class GoodsDetailActivity: BaseActivity() {

    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

        mCartBadge = QBadgeView(this)
    }

    //加载购物车数量
    private fun loadCartSize() {
        setCartBadge()
    }

    //监听购物车数量变化
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe(){
                    setCartBadge()
                }.registerInBus(this)
    }

    //设置购物车标签
    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(6f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    //Bus取消监听
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}