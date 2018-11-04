package com.kotlinteam.ui

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlinteam.R
import com.kotlinteam.base.router.NavigationRouter
import com.kotlinteam.base.ui.BaseDataBindingActivity
import com.kotlinteam.databinding.ActivityHomeBinding

@Route(path = NavigationRouter.APP_HOME_ACTIVITY)
class HomeActivity : BaseDataBindingActivity<ActivityHomeBinding>(R.layout.activity_home){

    override fun initData() {
        val arr = arrayOf("1", "2", "3", "4", "5", "6")
        viewDataBinding.appAcHomeCardView.setContentResource(R.layout.item_card_view)
        viewDataBinding.appAcHomeCardView.setAdapter(object : ArrayAdapter<String>(applicationContext, R.layout.item_card_view) {
            override fun getView(position: Int, contentView: View?, parent: ViewGroup): View {
                val v = contentView!!.findViewById(R.id.item_card_view_tv) as TextView
                v.text = arr[position % arr.size]
                return contentView
            }

            override fun getCount(): Int {
                return Integer.MAX_VALUE
            }
        })
    }

}