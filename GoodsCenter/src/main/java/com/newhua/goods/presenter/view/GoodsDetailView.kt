package com.newhua.goods.presenter.view

import com.newhua.goods.data.protocol.Goods
import com.newhua.mall.base.presenter.view.BaseView

interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    //加入购物车
    fun onAddCartResult(result: Int)
}