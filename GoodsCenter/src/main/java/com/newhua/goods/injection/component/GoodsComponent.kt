package com.newhua.goods.injection.component

import com.newhua.goods.injection.module.CartModule
import com.newhua.goods.injection.module.GoodsModule
import com.newhua.goods.ui.activity.GoodsActivity
import com.newhua.goods.ui.fragment.GoodsDetailTabOneFragment
import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import dagger.Component

/*
    商品Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}