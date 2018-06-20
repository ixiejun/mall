package com.newhua.pay.injection.module

import com.newhua.pay.service.PayService
import com.newhua.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * 支付Module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService {
        return payService
    }
}