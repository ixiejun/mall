package com.newhua.mall.user.injection.module

import com.newhua.mall.user.service.UploadService
import com.newhua.mall.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }
}