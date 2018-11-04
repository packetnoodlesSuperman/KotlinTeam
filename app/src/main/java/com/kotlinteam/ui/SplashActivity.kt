package com.kotlinteam.ui

import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlinteam.base.R
import com.kotlinteam.base.router.NavigationRouter
import com.kotlinteam.base.ui.BaseDataBindingActivity
import com.kotlinteam.databinding.ActivitySplashBinding

class SplashActivity : BaseDataBindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initData() {
        viewDataBinding.tvAcSplashContent.setText("欢迎进入Kotlin_Demo项目")
        viewDataBinding.tvAcSplashContent.setOnClickListener({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        })
    }
}