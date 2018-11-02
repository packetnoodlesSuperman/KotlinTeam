package com.example.wangzhiwei.qtz_kotlin.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import android.view.KeyEvent
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.example.wangzhiwei.qtz_kotlin.R
import com.example.wangzhiwei.qtz_kotlin.base.BaseActivity
import com.example.wangzhiwei.qtz_kotlin.bean.AdvertisingWithMenuBean
import com.example.wangzhiwei.qtz_kotlin.ui.presenter.AdvertisingPresenter
import com.example.wangzhiwei.qtz_kotlin.util.getStatusBarHeight
import kotlinx.android.synthetic.main.activity_advertising.*

class AdvertisingActivity : BaseActivity<AdvertisingPresenter>() {
    override val infalteId: Int
        get() = R.layout.activity_advertising

    private var timer: CountDownTimer? = null

    override fun initView() {
        txt_jump.apply {
            var params = layoutParams as RelativeLayout.LayoutParams
            params.topMargin += getStatusBarHeight()
            layoutParams = params
            setOnClickListener {
                timer?.cancel()
                goMainActivity()
            }
        }

        timer = object : CountDownTimer(6000, 1000) {
            override fun onFinish() {
                goMainActivity()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished == 0L) {
                    txt_jump.text = "跳过0s"
                } else {
                    txt_jump.text = "跳过${millisUntilFinished / 1000}s"
                }
            }
        }
       // timer?.start()

    }

    override fun initData() {
        mPresenter?.apply {
            getAdvertisingPic("2.3.1")
        }
    }

    override fun initPresenter(): AdvertisingPresenter? {
        return AdvertisingPresenter()
    }

    override fun initStatusBarColor(color: Int, fitWindow: Boolean) {
        super.initStatusBarColor(android.R.color.transparent, false)
    }

    private fun goMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun onUpdatePic(data: String) {
        Glide.with(this)
                .load(data)
                .into(img_advertising)
    }

    fun onUpdateFailed(msg: String) {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }


}
