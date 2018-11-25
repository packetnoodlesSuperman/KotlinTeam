package com.wangzwi1l.core.api

import android.util.Log
import com.kotlinteam.core.BuildConfig
import com.wangzwi1l.core.converter.CustomConverter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


object Api {

    private const val TIME = 15L

    private val requestMap: HashMap<String, Any> by lazy {
        HashMap<String, Any>()
    }

    private val retrofitMap :HashMap<String,Retrofit> by lazy {
        HashMap<String,Retrofit>()
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            if (BuildConfig.DEBUG) {
                Log.e("API", "okhttp message --------------------->  \n$it")
            }
        })
    }

    @JvmStatic
    fun <T> newRequest(clazz: Class<T>, baseUrl: String = "https://api.qtz360.com/api1.1.0/"): T {
        var retrofit: Retrofit
        if (retrofitMap.containsKey(baseUrl)){
            retrofit = retrofitMap[baseUrl]!!
        } else {
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder().let {
                it.addInterceptor(loggingInterceptor)
                it.connectTimeout(TIME, TimeUnit.SECONDS)
                it.readTimeout(TIME, TimeUnit.SECONDS)
                it.build()
            }
            retrofit = Retrofit.Builder().let {
                it.baseUrl(baseUrl)
                it.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                it.addConverterFactory(CustomConverter.instance)
                it.client(client)
                it.build()
            }
            retrofitMap[baseUrl] = retrofit
        }

        if (requestMap.containsKey("$baseUrl ${clazz.simpleName}")) {
            return requestMap["$baseUrl ${clazz.simpleName}"] as T
        } else {
            synchronized(this) {
                val result = retrofit.create(clazz)!!
                requestMap["$baseUrl ${clazz.simpleName}"] = result as Any
                return result
            }
        }
    }
}