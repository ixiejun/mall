package com.newhua.mall.base.widgets

import android.content.Context
import android.widget.ImageView
import com.newhua.mall.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/*
    Banner图片加载器
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}