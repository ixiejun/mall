package com.newhua.mall.user.injection.component

import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import com.newhua.mall.user.injection.module.UploadModule
import com.newhua.mall.user.injection.module.UserModule
import com.newhua.mall.user.ui.activity.*
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(UserModule::class,
        UploadModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: ForgetPwdActivity)

    fun inject(activity: ResetPwdActivity)

    fun inject(activity: UserInfoActivity)

}