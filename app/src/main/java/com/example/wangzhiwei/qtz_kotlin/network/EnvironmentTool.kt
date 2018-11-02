package com.example.wangzhiwei.qtz_kotlin.network

import com.example.wangzhiwei.qtz_kotlin.BuildConfig

object EnvironmentTool {
    val currtentEnvironment: String by lazy {
        BuildConfig.Environment
    }

    lateinit var url: String
    lateinit var h5: String
    lateinit var img: String

    init {
        when (currtentEnvironment) {
            "release" -> {
                url = "https://www.qtz360.com/v3.0.0/"
                h5 = "https://www.qtz360.com"
                img = "https://img.qtz360.com/mobile"
            }
            "test" -> {
                url = "https://api.qtz360.com/api1.1.0/"
                h5 = "https://api.qtz360.com"
                img = "http://pcek6maj9.bkt.clouddn.com/mobile"
            }
        }
    }
}