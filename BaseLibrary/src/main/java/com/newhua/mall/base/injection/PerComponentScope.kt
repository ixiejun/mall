package com.newhua.mall.base.injection

import javax.inject.Scope
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME

/*
    组件级别 作用域
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope