package com.newhua.mall.user.data.repository

import com.newhua.mall.base.data.net.RetrofitFactory
import com.newhua.mall.base.data.protocol.BaseResponse
import com.newhua.mall.user.data.api.UserApi
import com.newhua.mall.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun register(mobile: String, password: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, password, verifyCode))
    }

    /*
        用户登录
     */
    fun login(mobile:String, pwd:String, pushId:String): Observable<BaseResponse<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,pwd,pushId))
    }

    /*
        忘记密码
     */
    fun forgetPwd(mobile:String, verifyCode:String): Observable<BaseResponse<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(mobile,verifyCode))
    }

    /*
        重置密码
     */
    fun resetPwd(mobile:String, pwd:String): Observable<BaseResponse<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(mobile,pwd))
    }

    /*
        编辑用户资料
     */
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String):Observable<BaseResponse<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(userIcon,userName,userGender,userSign))
    }
}