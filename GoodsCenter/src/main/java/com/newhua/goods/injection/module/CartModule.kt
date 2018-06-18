package com.newhua.goods.injection.module

import com.newhua.goods.service.CartService
import com.newhua.goods.service.Impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }

}