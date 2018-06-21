package com.newhua.mall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.newhua.goods.common.GoodsConstant
import com.newhua.goods.event.UpdateCartSizeEvent
import com.newhua.goods.ui.fragment.CartFragment
import com.newhua.goods.ui.fragment.CategoryFragment
import com.newhua.mall.R
import com.newhua.mall.base.common.AppManager
import com.newhua.mall.base.utils.AppPrefsUtils
import com.newhua.mall.provider.event.MessageBadgeEvent
import com.newhua.mall.ui.fragment.HomeFragment
import com.newhua.mall.ui.fragment.MeFragment
import com.newhua.message.ui.fragment.MessageFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var pressTime: Long = 0

    private val mStack = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()
    }

    //初始化Fragment栈管理
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mHomeFragment)
        manager.add(R.id.mContaier, mCategoryFragment)
        manager.add(R.id.mContaier, mCartFragment)
        manager.add(R.id.mContaier, mMsgFragment)
        manager.add(R.id.mContaier, mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

    private fun initView() {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContaier, HomeFragment())
        manager.commit()
    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
        mBottomNavBar.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for(fragment in mStack) {
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }

    //监听购物车数量变化
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe(){
                    loadCartSize()
                }.registerInBus(this)

        Bus.observe<MessageBadgeEvent>()
                .subscribe {
                    t: MessageBadgeEvent ->
                    run {
                        mBottomNavBar.checkMsgBadge(t.isVisible)
                    }
                }.registerInBus(this)
    }

    //加载购物车数量
    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    //取消Bus监听
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /**
     * 双击两次退出
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }

    }
}
