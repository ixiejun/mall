package com.newhua.mall.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.newhua.mall.base.common.BaseConstant
import com.newhua.mall.base.utils.AppPrefsUtils
import com.newhua.mall.provider.router.RouterPath

/*
    顶级函数，判断是否登录
 */
fun isLogined():Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method: ()->Unit) {
    if(isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}
