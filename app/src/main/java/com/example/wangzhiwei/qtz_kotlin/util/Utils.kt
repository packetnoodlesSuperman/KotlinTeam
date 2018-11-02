package com.example.wangzhiwei.qtz_kotlin.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.Fragment

/**
 * 扩展方法
 */
fun Activity.toAppDetail(requestCode: Int = -1) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.data = Uri.fromParts("package", packageName, null)
    startActivityForResult(intent, requestCode)
}

fun Activity.getStatusBarHeight(): Int {
    var id = this.resources.getIdentifier("status_bar_height",
            "dimen", "android")
    return if (id > 0) {
        this.resources.getDimensionPixelSize(id)
    } else {
        0
    }
}

fun Fragment.getStatusBarHeight(): Int {
    var id = this.resources.getIdentifier("status_bar_height",
            "dimen", "android")
    return if (id > 0) {
        this.resources.getDimensionPixelSize(id)
    } else {
        0
    }
}
