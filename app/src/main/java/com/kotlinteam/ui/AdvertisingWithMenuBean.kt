package com.kotlinteam.ui


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdvertisingWithMenuBean(
        val code: String,
        val message: String,
        val data: List<Data>
) : Parcelable

@Parcelize
data class Data(
        val title: String,
        val commPic: String,
        val actHdPic: String,
        val hdPic: String,
        val modifyDate: Long,
        val type: Int,
        val actCommPic: String
) : Parcelable