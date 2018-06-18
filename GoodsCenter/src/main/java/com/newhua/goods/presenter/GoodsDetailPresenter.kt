package com.newhua.goods.presenter

import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.data.protocol.Goods
import com.newhua.goods.presenter.view.GoodsDetailView
import com.newhua.goods.service.CartService
import com.newhua.goods.service.GoodsService
import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.mall.base.utils.AppPrefsUtils
import javax.inject.Inject

/**
 * 商品详情Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    /**
     * 获取商品详情
     */
    fun getGoodsDetailList(goodsId: Int) {
        if(!checkNetWork()) {
            return
        }

        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).execute(object: BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)
    }

    /**
     * 加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if(!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon,
                goodsPrice, goodsCount, goodsSku).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, t)
                mView.onAddCartResult(t)
            }
        }, lifecycleProvider)
    }
}