package com.wangzwi1l.core.converter

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomConverter constructor(val gson: Gson) : Converter.Factory() {

    companion object {
        val instance by lazy { CustomConverter(Gson()) }
    }

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return CustomResponseBodyConverter<Any>(gson, type)
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return CustomRequestBodyConverter<Any>(gson, type)
    }
}