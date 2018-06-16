package com.newhua.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.newhua.goods.R
import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.data.protocol.Goods
import com.newhua.goods.event.GoodsDetailImageEvent
import com.newhua.goods.injection.component.DaggerGoodsComponent
import com.newhua.goods.injection.module.GoodsModule
import com.newhua.goods.presenter.GoodsDetailPresenter
import com.newhua.goods.presenter.view.GoodsDetailView
import com.newhua.mall.base.ui.fragment.BaseMvpFragment
import com.newhua.mall.base.utils.YuanFenConverter
import com.newhua.mall.base.widgets.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    private fun loadData() {
        mPresenter.getGoodsDetailList(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    //Dagger 注册
    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
    }

    override fun onAddCartResult(result: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}