package com.wangzwi1l.core.converter

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.Closeable
import java.io.IOException
import java.lang.reflect.Type

class CustomResponeBodyConverter<T>(val gson: Gson, val type: Type) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T {
        return value.let {
            val reader = it.charStream()
            try {
                val jsonReader = JsonReader(reader)
                jsonReader.isLenient = true
                gson.fromJson(jsonReader, type)
            } finally {
                closeQuietly(reader)
            }

        }
    }

    fun closeQuietly(closeable: Closeable) {
        try {
            closeable.close()
        } catch (e: IOException) {

        }

    }
}