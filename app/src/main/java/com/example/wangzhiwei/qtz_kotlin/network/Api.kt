package com.example.wangzhiwei.qtz_kotlin.network

import android.util.Log
import com.example.wangzhiwei.qtz_kotlin.BuildConfig
import com.example.wangzhiwei.qtz_kotlin.bean.AdvertisingWithMenuBean
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api : ApiServer {
    private val sApiServer: ApiServer
    var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            if (BuildConfig.DEBUG) {
                Log.e("API", "okhttp message --------> $message")
            }
        }
    })

    init {
        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        val client: OkHttpClient = OkHttpClient.Builder().let {
            it.addInterceptor(loggingInterceptor)
            it.connectTimeout(15, TimeUnit.SECONDS)
            it.readTimeout(15, TimeUnit.SECONDS)
            it.build()
        }

        val retrofit: Retrofit = Retrofit.Builder().let {
            it.baseUrl(EnvironmentTool.url)
            it.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            it.addConverterFactory(GsonConverterFactory.create())
            it.client(client)
            it.build()
        }
        sApiServer = retrofit.create<ApiServer>(ApiServer::class.java)
    }

    override fun getAdvertisingPicWithMenuIcon(versionName: String): Observable<AdvertisingWithMenuBean> {
        return sApiServer.getAdvertisingPicWithMenuIcon(versionName)
    }

}