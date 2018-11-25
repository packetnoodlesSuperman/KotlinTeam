package com.kotlinteam.base.ui

import android.databinding.ViewDataBinding
import android.os.Bundle
import com.wangzwi1l.core.api.Api

abstract class BaseActivity<out P: BasePresenter<*>>(var contentViewLayout: Int) : BaseDataBindingActivity<ViewDataBinding>(contentViewId = -1) {

    val mPresenter: P? by lazy {
        initPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewLayout)
        mPresenter?.onAttachView(this)
        initView()
        initData()
    }

    abstract fun initView()

    abstract override fun initData()

    abstract fun initPresenter(): P?


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mPresenter?.onDetachView()
    }

}