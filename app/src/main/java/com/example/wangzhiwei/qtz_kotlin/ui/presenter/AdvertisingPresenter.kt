package com.example.wangzhiwei.qtz_kotlin.ui.presenter

import com.example.wangzhiwei.qtz_kotlin.base.BasePresenter
import com.example.wangzhiwei.qtz_kotlin.bean.AdvertisingWithMenuBean
import com.example.wangzhiwei.qtz_kotlin.network.Api
import com.example.wangzhiwei.qtz_kotlin.network.EnvironmentTool
import com.example.wangzhiwei.qtz_kotlin.network.RxCallBack
import com.example.wangzhiwei.qtz_kotlin.ui.activity.AdvertisingActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AdvertisingPresenter : BasePresenter<AdvertisingActivity>() {
    fun getAdvertisingPic(name: String) {
        Api.getAdvertisingPicWithMenuIcon(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : RxCallBack<AdvertisingWithMenuBean>(this) {
                    override fun onFiled(msg: String) {
                        getView()?.apply {
                            onUpdateFailed(msg)
                        }

                    }

                    override fun onSuccess(t: AdvertisingWithMenuBean) {
                        getView()?.apply {
                            if (t.code == "0000") {
                                for (data in t.data) {
                                    if (data.type == 4) {
                                        onUpdatePic(EnvironmentTool.img + data.hdPic)
                                        break
                                    }
                                }
                            }

                        }
                    }
                })
    }
}