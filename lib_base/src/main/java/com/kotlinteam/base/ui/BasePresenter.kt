package com.kotlinteam.base.ui

import java.lang.ref.WeakReference

abstract class BasePresenter<out V> {

    var view: WeakReference<@UnsafeVariance V>? = null

    open fun onAttachView(v: @UnsafeVariance V) {
        view = WeakReference(v)
    }

    open fun onDetachView() {
        if (view == null) {
            return
        }
        view?.clear()
        view = null
    }

    open fun getView(): V? {
        if (view == null) {
            return null
        }
        return view?.get()
    }

}