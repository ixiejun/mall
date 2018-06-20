package com.newhua.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newhua.mall.R
import com.newhua.mall.base.ext.loadUrl
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.fragment.BaseFragment
import com.newhua.mall.base.utils.AppPrefsUtils
import com.newhua.mall.provider.common.ProviderConstant
import com.newhua.mall.provider.common.afterLogin
import com.newhua.mall.provider.common.isLogined
import com.newhua.mall.ui.activity.SettingActivity
import com.newhua.mall.user.ui.activity.LoginActivity
import com.newhua.mall.user.ui.activity.UserInfoActivity
import com.newhua.order.common.OrderConstant
import com.newhua.order.common.OrderStatus
import com.newhua.order.ui.activity.OrderActivity
import com.newhua.order.ui.activity.ShipAddressActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

/*
    "我的"界面
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)


    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        加载初始数据
     */
    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }


    override fun onClick(view: View) {
        when(view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if(isLogined()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
            }

            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            }

            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            }

            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            }

            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mAddressTv -> {
                startActivity<ShipAddressActivity>()
            }

            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }
}