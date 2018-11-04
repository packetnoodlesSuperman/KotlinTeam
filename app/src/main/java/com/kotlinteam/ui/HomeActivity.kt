package com.kotlinteam.ui

import com.kotlinteam.R
import com.kotlinteam.base.ui.BaseActivity

class HomeActivity : BaseActivity<HomePresenter<HomeActivity>>(R.layout.activity_home){

    override fun initView() {

    }

    override fun initData() {
    }

    override fun initPresenter(): HomePresenter<HomeActivity>? = HomePresenter()


}
