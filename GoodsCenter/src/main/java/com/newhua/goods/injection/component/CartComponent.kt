package com.newhua.goods.injection.component

import com.newhua.goods.injection.module.CartModule
import com.newhua.goods.ui.fragment.CartFragment
import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import dagger.Component

/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}