package com.newhua.mall.base.rx

import com.newhua.mall.base.data.protocol.BaseResponse
import rx.Observable
import rx.functions.Func1

class BaseFunc<T> : Func1<BaseResponse<T>, Observable<T>> {

    override fun call(t: BaseResponse<T>): Observable<T> {
        if(t.status != 0) {
            return rx.Observable.error(BaseException(t.status, t.message))
        }
        return rx.Observable.just(t.data)
    }
}