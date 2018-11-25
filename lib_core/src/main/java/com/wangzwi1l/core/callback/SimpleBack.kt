package com.wangzwi1l.core.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class SimpleBack<T> : Callback<T>, ICallback<T> {
    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFailed(t.toString())
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response == null) {
            onFailed("that response must be null")
        } else {
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    onFailed("that response body must be null")
                } else {
                    onSuccessed(body)
                }
            } else {
                onFailed("request response maybe error with code: ${response.code()}")
            }
        }
    }

    override fun onTokenOverTime() {
    }
}