package com.example.wangzhiwei.qtz_kotlin.network

import com.example.wangzhiwei.qtz_kotlin.bean.AdvertisingWithMenuBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServer {
    /**
     * 获取首页下面的按钮,以及StartUpActivity页面的图片
     *
     * @param versionName
     * @return
     */
    @GET("rest/iconPic")
    fun getAdvertisingPicWithMenuIcon(@Query("version")versionName:String): Observable<AdvertisingWithMenuBean>

}