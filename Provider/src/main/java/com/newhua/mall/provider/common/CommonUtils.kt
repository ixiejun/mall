package com.newhua.mall.provider.common

import com.newhua.mall.base.common.BaseConstant
import com.newhua.mall.base.utils.AppPrefsUtils

/*
    顶级函数，判断是否登录
 */
fun isLogined():Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}
