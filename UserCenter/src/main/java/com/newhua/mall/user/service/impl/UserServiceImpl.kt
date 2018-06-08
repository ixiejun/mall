package com.newhua.mall.user.service.impl

import com.newhua.mall.base.ext.convertBoolean
import com.newhua.mall.user.data.repository.UserRepository
import com.newhua.mall.user.service.UserService
import rx.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, password: String, verifyCode: String): Observable<Boolean> {

        return repository.register(mobile, password, verifyCode).convertBoolean()
    }

}