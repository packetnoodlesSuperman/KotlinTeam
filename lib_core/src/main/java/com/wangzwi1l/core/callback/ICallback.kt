package com.wangzwi1l.core.callback

interface ICallback<T> {
    /**
     * 请求成功
     */
    fun onSuccessed(t: T)

    /**
     * 请求失败
     */
    fun onFailed(msg: String)

    /**
     * 登录过期
     */
    fun onTokenOverTime()
}