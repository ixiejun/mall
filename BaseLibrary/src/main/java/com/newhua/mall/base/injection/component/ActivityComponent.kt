package com.newhua.mall.base.injection.component

import android.app.Activity
import android.content.Context
import com.newhua.mall.base.injection.ActivityScope
import com.newhua.mall.base.injection.module.ActivityModule
import com.newhua.mall.base.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}