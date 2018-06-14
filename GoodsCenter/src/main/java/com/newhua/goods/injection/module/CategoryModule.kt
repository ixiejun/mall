package com.newhua.goods.injection.module

import com.newhua.goods.service.CategoryService
import com.newhua.goods.service.Impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * 商品分类Module
 */
@Module
class CategoryModule {

    @Provides
    fun provideCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}