package com.newhua.mall.user.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.mall.user.injection.module.UserModule
import com.newhua.mall.user.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}