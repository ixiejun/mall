package com.newhua.pay.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.pay.injection.module.PayModule
import com.newhua.pay.ui.activity.CashRegisterActivity
import dagger.Component

/**
 * 支付Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}