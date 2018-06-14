package com.newhua.mall.ui.activity

import android.os.Bundle
import com.newhua.mall.R
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseActivity
import com.newhua.mall.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}