package com.newhua.goods.presenter.view

import com.newhua.goods.data.protocol.Category
import com.newhua.mall.base.presenter.view.BaseView

interface CategoryView : BaseView {

    //获取商品分类列表
    fun onGetCategoryResult(result: MutableList<Category>?)
}