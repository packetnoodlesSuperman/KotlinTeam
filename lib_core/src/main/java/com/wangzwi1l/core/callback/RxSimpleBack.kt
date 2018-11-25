package com.wangzwi1l.core.callback

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 配合Rxjava使用的回调
 */
abstract class RxSimpleBack<T>(private val compositeDisposable: CompositeDisposable?) : Observer<T>, ICallback<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable?.apply {
            add(d)
        }
    }

    override fun onNext(t: T) {
        onSuccessed(t)
    }

    override fun onError(e: Throwable) {
        onFailed("find a error: ${e.message}")
    }

    override fun onTokenOverTime() {
    }

}