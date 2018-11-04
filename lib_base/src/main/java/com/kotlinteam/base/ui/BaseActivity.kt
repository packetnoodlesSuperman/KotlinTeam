package com.kotlinteam.base.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<out P: BasePresenter<*>>(var contentViewId: Int) : AppCompatActivity() {

    val mPresenter: P? by lazy {
        initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)
        mPresenter?.onAttachView(this)
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initPresenter(): P?


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mPresenter?.onDetachView()
    }

}