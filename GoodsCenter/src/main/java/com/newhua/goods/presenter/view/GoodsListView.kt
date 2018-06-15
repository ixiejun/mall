package com.newhua.goods.presenter.view

import com.newhua.goods.data.protocol.Goods
import com.newhua.mall.base.presenter.view.BaseView

/**
 * 商品列表 视图回调
 */
interface GoodsListView : BaseView {
    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}