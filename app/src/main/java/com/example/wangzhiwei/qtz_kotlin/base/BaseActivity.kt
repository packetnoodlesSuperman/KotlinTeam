package com.example.wangzhiwei.qtz_kotlin.base

import android.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar

abstract class BaseActivity<out T : BasePresenter<*>> : AppCompatActivity() {

    open val TAG: String by lazy<String> {
        javaClass.simpleName
    }

    val mPresenter: T? by lazy {
        initPresenter()
    }

    abstract val infalteId: Int

    abstract fun initView()

    abstract fun initData()

    abstract fun initPresenter(): T?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * 初始化状态栏
         */
        initStatusBarColor()
        setContentView(infalteId)
        mPresenter?.attachView(this)
        initView()
        initData()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mPresenter?.unAttachView()
    }

    /**
     * 默认参数， 另外 ImmersionBar 接收的颜色对象是R.id的形式
     */
    open fun initStatusBarColor(color: Int = R.color.white, fitWindow: Boolean = true) {
        ImmersionBar.with(this)?.apply {
            statusBarColor(color)
            fitsSystemWindows(fitWindow)
            navigationBarColor(R.color.white)
            init()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unAttachView()
        ImmersionBar.with(this)
                .destroy()
    }

}