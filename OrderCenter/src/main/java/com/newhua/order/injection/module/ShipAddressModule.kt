package com.newhua.order.injection.module

import com.newhua.order.service.Impl.ShipAddressServiceImpl
import com.newhua.order.service.ShipAddressService
import dagger.Module
import dagger.Provides

//收货地址Module
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }
}