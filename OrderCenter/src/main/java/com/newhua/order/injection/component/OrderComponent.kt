package com.newhua.order.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.order.injection.module.OrderModule
import com.newhua.order.ui.activity.OrderConfirmActivity
import com.newhua.order.ui.activity.OrderDetailActivity
import com.newhua.order.ui.fragment.OrderFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(orderConfirmActivity: OrderConfirmActivity)
    fun inject(orderFragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)
}