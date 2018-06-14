package com.newhua.goods.service.Impl

import com.newhua.goods.data.protocol.Category
import com.newhua.goods.data.repository.CategoryRepository
import com.newhua.goods.service.CategoryService
import com.newhua.mall.base.ext.convert
import rx.Observable
import javax.inject.Inject

/**
 * 商品分类 业务层 实现类
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }
}