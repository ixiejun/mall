package com.newhua.goods.injection.module

import com.newhua.goods.service.GoodsService
import com.newhua.goods.service.Impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/*
    商品Module
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodservice(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }

}