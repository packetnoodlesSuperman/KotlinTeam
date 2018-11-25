package com.wangzwi1l.core.converter

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

class CustomRequestBodyConverter<T>(val gson: Gson, val type: Type) : Converter<T, RequestBody> {

    val MEDIA_TYPE: MediaType? = MediaType.parse("application/json; charset=UTF-8")
    val UTF_8: Charset = Charset.forName("UTF-8")

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        try {
            gson.toJson(value, type, writer)
            writer.flush()
        } catch (e: IOException) {
            throw AssertionError(e)
        }
        return RequestBody.create(MEDIA_TYPE, buffer.readByteArray())
    }
}