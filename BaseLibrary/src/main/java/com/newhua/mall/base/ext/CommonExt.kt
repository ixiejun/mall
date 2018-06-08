package com.newhua.mall.base.ext

import android.view.View
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.mall.base.rx.BaseFunc
import com.newhua.mall.base.rx.BaseFuncBoolean
import com.newhua.mall.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>){
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResponse<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: ()->Unit) {
    this.setOnClickListener { method() }
}