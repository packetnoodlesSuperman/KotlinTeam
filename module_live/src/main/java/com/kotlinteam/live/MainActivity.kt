package com.kotlinteam.live

import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlinteam.base.router.NavigationRouter
import com.kotlinteam.base.ui.BaseDataBindingActivity
import com.kotlinteam.live.databinding.LiveActivityMainBinding

@Route(path = NavigationRouter.LIVE_NAVIGATION)
class MainActivity : BaseDataBindingActivity<LiveActivityMainBinding>(R.layout.live_activity_main) {
    override fun initData() {

    }
}