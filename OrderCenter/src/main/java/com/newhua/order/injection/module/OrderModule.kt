package com.newhua.order.injection.module

import com.newhua.order.service.Impl.OrderServiceImpl
import com.newhua.order.service.OrderService
import dagger.Module
import dagger.Provides

//订单Module
@Module
class OrderModule {

    @Provides
    fun provideOrderService(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}