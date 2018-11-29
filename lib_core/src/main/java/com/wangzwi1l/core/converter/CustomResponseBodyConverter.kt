package com.wangzwi1l.core.converter

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

class CustomResponseBodyConverter<T>(private val gson: Gson, val type: Type) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T {
        return gson.fromJson(value.string(), type)
    }
}