package com.newhua.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.newhua.goods.R
import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.data.protocol.Goods
import com.newhua.goods.injection.component.DaggerGoodsComponent
import com.newhua.goods.injection.module.GoodsModule
import com.newhua.goods.presenter.GoodsListPresenter
import com.newhua.goods.presenter.view.GoodsListView
import com.newhua.goods.ui.adapter.GoodsAdapter
import com.newhua.mall.base.ext.startLoading
import com.newhua.mall.base.rx.BaseSubscriber
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.mall.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var mGoodsAdapter: GoodsAdapter

    private var mCurrentPage: Int = 1

    private var mMaxPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })


    }

    /**
     * 加载数据
     */
    private fun loadData() {
        if(intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }
    }

    /**
     * Dagger注入
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }

    /**
     * 获取列表后回调
     */
    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if(result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if(mCurrentPage == 1) {
                mGoodsAdapter.setData(result)
            } else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 初始化刷新视图
     */
    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundDrawableRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    /**
     * 上拉加载更多
     */
    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if(mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            true
        } else {
            false
        }

    }

    /**
     * 下拉加载第一页
     */
    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }
}