package com.kotlinteam.live

import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlinteam.base.router.RouterPath
import com.kotlinteam.base.ui.BaseDataBindingActivity
import com.kotlinteam.live.databinding.LiveActivityMainBinding

@Route(path = RouterPath.MoudleLive.LIVE_NAVIGATION)
class LiveMainActivity : BaseDataBindingActivity<LiveActivityMainBinding>(R.layout.live_activity_main) {
    override fun initData() {

    }
}