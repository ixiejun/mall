package com.newhua.goods.presenter

import com.newhua.goods.data.protocol.Goods
import com.newhua.goods.presenter.view.GoodsDetailView
import com.newhua.goods.service.GoodsService
import com.newhua.mall.base.ext.execute
import com.newhua.mall.base.presenter.BasePresenter
import com.newhua.mall.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * 商品详情Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

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
}