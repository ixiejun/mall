package com.newhua.pay.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.newhua.mall.base.ext.onClick
import com.newhua.mall.base.ui.activity.BaseMvpActivity
import com.newhua.mall.base.utils.YuanFenConverter
import com.newhua.mall.provider.common.ProviderConstant
import com.newhua.mall.provider.router.RouterPath
import com.newhua.pay.R
import com.newhua.pay.injection.component.DaggerPayComponent
import com.newhua.pay.injection.module.PayModule
import com.newhua.pay.presenter.PayPresenter
import com.newhua.pay.presenter.view.PayView
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * 收银台界面
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {

    //订单号
    var mOrderId: Int = 0

    //订单总价格
    var mTotalPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)

        ARouter.getInstance().inject(this)

        intiView()
        initData()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(activityComponent)
                .payModule(PayModule()).build().inject(this)
        mPresenter.mView = this
    }

    //初始化视图
    private fun intiView() {
        mAlipayTypeTv.isSelected = true
        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
        mPayBtn.onClick(this)
    }

    //初始化数据
    private fun initData() {
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(ProviderConstant.KEY_ORDER_PRICE, -1)

        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
    }

    //获取支付签名回调
    override fun onGetSignResult(result: String) {
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if(resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    //支付订单回调
    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
    }

    //点击事件
    override fun onClick(v: View) {
        when(v.id) {
            R.id.mAlipayTypeTv -> {updatePayType(true, false, false)}
            R.id.mWeixinTypeTv -> {updatePayType(false, true, true)}
            R.id.mBankCardTypeTv -> {updatePayType(false, false, true)}
            R.id.mPayBtn -> {
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }
        }
    }

    //选择支付类型,UI变化
    private fun updatePayType(isAliPay: Boolean, isWeixinPay: Boolean, isBankCardPay: Boolean) {
        mAlipayTypeTv.isSelected = isAliPay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCardPay
    }
}