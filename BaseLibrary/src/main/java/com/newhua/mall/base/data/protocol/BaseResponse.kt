package com.newhua.mall.base.data.protocol

class BaseResponse<T>(val status: Int, val message: String, val data:T) {

}