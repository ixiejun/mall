package com.newhua.goods.ui.activity

import android.os.Bundle
import com.newhua.goods.R
import com.newhua.goods.ui.fragment.CartFragment
import com.newhua.mall.base.ui.activity.BaseActivity

/**
 * 购物车Activity
 * 只是Fragment的一个壳
 */
class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}