package com.newhua.goods.data.api

import com.newhua.goods.data.protocol.Category
import com.newhua.goods.data.protocol.GetCategoryReq
import com.newhua.mall.base.data.protocol.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface CategoryApi {

    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq) : Observable<BaseResponse<MutableList<Category>?>>
}