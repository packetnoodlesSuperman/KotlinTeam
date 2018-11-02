package com.example.wangzhiwei.qtz_kotlin.network

import com.example.wangzhiwei.qtz_kotlin.base.BasePresenter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class RxCallBack<T>(val mPresenter: BasePresenter<*>) : Observer<T>,ICallback<T> {

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        mPresenter?.apply {
            mDisposables.add(d)
        }
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFiled("请求出错了，请稍后尝试！")
    }

}