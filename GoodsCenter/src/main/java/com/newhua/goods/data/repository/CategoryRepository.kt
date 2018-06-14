package com.newhua.goods.data.repository

import com.newhua.goods.data.api.CategoryApi
import com.newhua.goods.data.protocol.Category
import com.newhua.goods.data.protocol.GetCategoryReq
import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import rx.Observable
import javax.inject.Inject

/**
 * 商品分类,数据层
 */
class CategoryRepository @Inject constructor() {

    /**
     * 获取商品分类
     */
    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}