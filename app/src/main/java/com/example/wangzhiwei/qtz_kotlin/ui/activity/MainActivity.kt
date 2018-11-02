package com.example.wangzhiwei.qtz_kotlin.ui.activity

import com.example.wangzhiwei.qtz_kotlin.R
import com.example.wangzhiwei.qtz_kotlin.base.BaseActivity
import com.example.wangzhiwei.qtz_kotlin.base.BasePresenter
import com.example.wangzhiwei.qtz_kotlin.ui.presenter.MainPresenter

class MainActivity : BaseActivity<MainPresenter<MainActivity>>() {
    override val infalteId: Int
        get() = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initPresenter(): MainPresenter<MainActivity>? {
       return MainPresenter()
    }
}
