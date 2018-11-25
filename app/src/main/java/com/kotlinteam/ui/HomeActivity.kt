package com.kotlinteam.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlinteam.R
import com.kotlinteam.base.router.RouterPath
import com.kotlinteam.base.ui.BaseDataBindingActivity
import com.kotlinteam.databinding.ActivityHomeBinding
import com.wangzwi1l.core.api.Api
import com.wangzwi1l.core.callback.RxSimpleBack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeActivity : BaseDataBindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun initData() {
        Api.newRequest(IApiModule::class.java)
                .getAdvertisingPicWithMenuIcon("2.3.1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : RxSimpleBack<AdvertisingWithMenuBean>(null) {
                    override fun onSuccessed(t: AdvertisingWithMenuBean) {
                        Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailed(msg: String) {
                        Toast.makeText(this@HomeActivity, msg, Toast.LENGTH_SHORT).show()
                    }

                })

        val arr = arrayOf("kotlin_module_live", "kotlin_module_video", "kotlin_module_xxx")
        viewDataBinding.appAcHomeCardView.setContentResource(R.layout.item_card_view)
        viewDataBinding.appAcHomeCardView.setAdapter(object : ArrayAdapter<String>(applicationContext, R.layout.item_card_view) {
            override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {
                val v = contentView!!.findViewById(R.id.item_card_view_tv) as TextView
                v.text = arr[position % arr.size]
                v.setOnClickListener(View.OnClickListener {
                    if ("kotlin_module_live".equals(v.text)) {
                        ARouter.getInstance().build(RouterPath.MoudleLive.LIVE_NAVIGATION).navigation()
                    }
                })
                return contentView
            }

            override fun getCount(): Int {
                return Integer.MAX_VALUE
            }
        })
    }
}