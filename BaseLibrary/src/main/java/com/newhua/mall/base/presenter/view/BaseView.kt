package com.newhua.mall.base.presenter.view

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}