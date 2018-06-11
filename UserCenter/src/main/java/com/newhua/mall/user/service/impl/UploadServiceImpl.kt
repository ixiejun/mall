package com.newhua.mall.user.service.impl

import com.newhua.mall.base.ext.convert
import com.newhua.mall.user.data.repository.UploadRepository
import com.newhua.mall.user.service.UploadService
import rx.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor() : UploadService{

    @Inject
    lateinit var uploadRepository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return uploadRepository.getUploadToken().convert()
    }
}