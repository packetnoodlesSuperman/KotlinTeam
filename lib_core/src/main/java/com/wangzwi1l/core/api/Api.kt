package com.wangzwi1l.core.api

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

    @JvmStatic
    fun <T> newRequest(clazz: Class<T>, baseUrl: String = "https://api.qtz360.com/api1.1.0/"): T {
        var retrofit: Retrofit
        if (retrofitMap.containsKey(baseUrl)){
            retrofit = retrofitMap[baseUrl]!!
        } else {
            val builder :OkHttpClient.Builder= OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                })
                connectTimeout(TIME, TimeUnit.SECONDS)
                readTimeout(TIME, TimeUnit.SECONDS)
            }
            retrofit = Retrofit.Builder().apply {
                baseUrl(baseUrl)
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(CustomConverter.instance)
                client(builder.build())
            }.build()
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