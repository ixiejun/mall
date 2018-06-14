package com.newhua.goods.injection.component

import com.newhua.goods.injection.module.CategoryModule
import com.newhua.goods.ui.fragment.CategoryFragment
import com.newhua.mall.base.injection.PerComponentScope
import com.newhua.mall.base.injection.component.ActivityComponent
import dagger.Component

/**
 * 商品分类Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}