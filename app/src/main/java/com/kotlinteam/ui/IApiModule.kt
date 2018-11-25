package com.kotlinteam.ui

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiModule {
    @GET("rest/iconPic")
    fun getAdvertisingPicWithMenuIcon(@Query("version") versionName: String): Observable<AdvertisingWithMenuBean>
}