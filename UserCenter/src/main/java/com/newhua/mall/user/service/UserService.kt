package com.newhua.mall.user.service

import rx.Observable

interface UserService {
    fun register(mobile: String, password: String, verifyCode: String): Observable<Boolean>
}