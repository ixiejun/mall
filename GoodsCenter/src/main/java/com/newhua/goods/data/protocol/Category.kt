package com.newhua.goods.data.protocol

/**
 * 商品分类
 */
data class Category (val id: Int,
                     val categoryName: String,
                     val categoryIcon: String = "",
                     val parentId: Int,
                     var isSelected: Boolean = false)