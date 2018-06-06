package com.newhua.mall.base.common

import android.app.Application
import com.newhua.mall.base.injection.component.AppComponent
import com.newhua.mall.base.injection.component.DaggerAppComponent
import com.newhua.mall.base.injection.module.AppModule

class BaseApplication :Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}