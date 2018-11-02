package com.example.wangzhiwei.qtz_kotlin.base

import android.support.v4.app.FragmentManager
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


abstract class BasePresenter<out T> {

    lateinit var mWakeView: WeakReference<@UnsafeVariance T>

    var fragmentManager: FragmentManager? = null

    val mDisposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun attachView(view: @UnsafeVariance T) {
        mWakeView = WeakReference(view)
    }

    fun unAttachView() {
        if (!mDisposables.isDisposed) {
            mDisposables.clear()
        }
        mWakeView.clear()
    }

    fun getView(): T? {
        return mWakeView.get()
    }


}