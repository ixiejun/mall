package com.newhua.mall.user.injection.module

import com.newhua.mall.user.service.UserService
import com.newhua.mall.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl) : UserService {
        return service
    }
}