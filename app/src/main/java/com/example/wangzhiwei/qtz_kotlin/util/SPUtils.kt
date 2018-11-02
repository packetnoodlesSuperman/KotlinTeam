package com.example.wangzhiwei.qtz_kotlin.util

import android.content.Context
import android.content.SharedPreferences

object SPUtils {
    const val fileName: String = "QTZ"

    fun putString(key: String, value: String, c: Context) {
        c.getSharedPreferences(fileName, Context.MODE_PRIVATE)
                .edit().apply {
                    putString(key, value)
                    apply()
                }
    }

    fun getString(key: String, c: Context): String? {
        return c.getSharedPreferences(fileName, Context.MODE_PRIVATE)
                .getString(key, null)
    }

    fun putInt(key: String, value: Int, c: Context) {
        c.getSharedPreferences(fileName, Context.MODE_PRIVATE)
                .edit().apply {
                    putInt(key, value)
                    apply()
                }
    }

    fun getInt(key: String, c: Context): Int {
        return c.getSharedPreferences(fileName, Context.MODE_PRIVATE)
                .getInt(key, -1)
    }
}