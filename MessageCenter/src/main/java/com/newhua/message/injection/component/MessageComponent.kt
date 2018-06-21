package com.newhua.message.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.message.injection.module.MessageModule
import com.newhua.message.ui.fragment.MessageFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(MessageModule::class))
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}