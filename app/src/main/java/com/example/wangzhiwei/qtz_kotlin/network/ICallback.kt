package com.example.wangzhiwei.qtz_kotlin.network

interface ICallback<T> {
    fun onFiled(msg: String)

    fun onSuccess(t: T)
}