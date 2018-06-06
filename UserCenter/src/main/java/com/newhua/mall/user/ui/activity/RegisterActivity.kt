package com.newhua.mall.user.ui.activity

import android.os.Bundle
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.mall.user.R
import com.newhua.mall.user.injection.component.DaggerUserComponent
import com.newhua.mall.user.injection.module.UserModule
import com.newhua.mall.user.presenter.RegisterPresenter
import com.newhua.mall.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        injectComponent()

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileEditText.text.toString(),
                    mPasswordEditText.text.toString(),
                    mVerifyCodeEditText.text.toString())
        }
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent
                .builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }


}
