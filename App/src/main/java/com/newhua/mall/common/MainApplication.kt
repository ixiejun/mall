package com.newhua.mall.common

import cn.jpush.android.api.JPushInterface
import com.newhua.mall.base.common.BaseApplication

class MainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}