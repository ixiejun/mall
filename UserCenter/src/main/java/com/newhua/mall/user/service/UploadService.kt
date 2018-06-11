package com.newhua.mall.user.service

import rx.Observable

interface UploadService {
    fun getUploadToken(): Observable<String>
}