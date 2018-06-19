package com.newhua.order.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.order.injection.module.ShipAddressModule
import com.newhua.order.ui.activity.ShipAddressActivity
import com.newhua.order.ui.activity.ShipAddressEditActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressEditActivity)
    fun inject(activity: ShipAddressActivity)
}