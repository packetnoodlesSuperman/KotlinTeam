package com.kotlinteam.base.ui

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<out V> {
    var weakDisposables: WeakReference<CompositeDisposable>? = null

    var view: WeakReference<@UnsafeVariance V>? = null

    open fun onAttachView(v: @UnsafeVariance V) {
        view = WeakReference(v)
        weakDisposables = WeakReference(CompositeDisposable())
    }

    open fun onDetachView() {
        view = view?.let {
            it.clear()
            null
        }
        val compositeDisposable = weakDisposables?.get()
        compositeDisposable?.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

    open fun getView(): V? {
        return view?.get()
    }

}