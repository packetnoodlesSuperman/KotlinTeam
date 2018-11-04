package com.kotlinteam.base.ui

import java.lang.ref.WeakReference

abstract class BasePresenter<out V> {

    var view: WeakReference<@UnsafeVariance V>? = null

    fun onAttachView(v: @UnsafeVariance V) {
        view = WeakReference(v)
    }

    fun onDetachView() {
        if (view == null) {
            return
        }
        view?.clear()
        view = null
    }

    fun getView(): V? {
        if (view == null) {
            return null
        }
        return view?.get()
    }

}