package com.newhua.goods.data.api

import com.newhua.goods.data.protocol.Category
import com.newhua.goods.data.protocol.GetCategoryReq
import com.newhua.mall.base.data.protocol.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * 商品分类接口
 */
interface CategoryApi {

    //获取商品分类列表
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq) : Observable<BaseResponse<MutableList<Category>?>>
}