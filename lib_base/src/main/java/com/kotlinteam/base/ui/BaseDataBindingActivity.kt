package com.kotlinteam.base.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseDataBindingActivity<CVB: ViewDataBinding>(var contentViewId: Int) : AppCompatActivity() {

    lateinit var viewDataBinding: CVB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this is BaseActivity<*>) {
            return
        }
        viewDataBinding = DataBindingUtil.setContentView<CVB>(this, contentViewId)
        initData()
    }

    abstract fun initData()

}