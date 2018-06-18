package com.newhua.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.newhua.goods.R
import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.data.protocol.CartGoods
import com.newhua.goods.event.UpdateCartSizeEvent
import com.newhua.goods.injection.component.DaggerCartComponent
import com.newhua.goods.injection.module.CartModule
import com.newhua.goods.presenter.CartListPresenter
import com.newhua.goods.presenter.view.CartListView
import com.newhua.goods.ui.adapter.CartGoodsAdapter
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ext.setVisible
import com.newhua.mall.base.ext.startLoading
import com.newhua.mall.base.ui.fragment.BaseMvpFragment
import com.newhua.mall.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * 购物车Fragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter: CartGoodsAdapter

    private var mTotalPrice: Long = 0

    //Dagger注册
    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent)
                .cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    //加载数据
    override fun onStart() {
        super.onStart()
        loadData()
    }

    //初始化视图和事件
    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context)
        mCartGoodsRv.adapter = mAdapter

        mHeaderBar.onClick {
            refreshEditStatus()
        }

    }

    private fun initObserve() {

    }

    private fun refreshEditStatus() {

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if(result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

        //本地存储并发送事件刷新UI
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size?:0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()
    }

    override fun onDeleteCartListResult(result: Boolean) {

    }

    override fun onSubmitCartListResult(result: Int) {

    }

    private fun updateTotalPrice() {

    }
}