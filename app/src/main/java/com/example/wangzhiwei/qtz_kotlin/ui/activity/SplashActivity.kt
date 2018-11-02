package com.example.wangzhiwei.qtz_kotlin.ui.activity

import android.Manifest
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import com.example.wangzhiwei.qtz_kotlin.R
import com.example.wangzhiwei.qtz_kotlin.base.BaseActivity
import com.example.wangzhiwei.qtz_kotlin.base.BasePresenter
import com.example.wangzhiwei.qtz_kotlin.util.toAppDetail
import kotlinx.android.synthetic.main.activity_splash.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class SplashActivity : BaseActivity<BasePresenter<*>>() {
    override val infalteId: Int
        get() = R.layout.activity_splash

    override fun initPresenter(): BasePresenter<*>? = null

    override fun initView() {

    }


    override fun initData() {
        goToAdvertisingActivity()
    }


    override fun initStatusBarColor(color: Int, fitWindow: Boolean) {
        super.initStatusBarColor(android.R.color.transparent, false)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 99) {
            goToAdvertisingActivity()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return true
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private fun goToAdvertisingActivity() {
        splash.postDelayed({
            startActivity(Intent(this, AdvertisingActivity::class.java))
            finish()
        }, 1000)
    }

    @OnPermissionDenied(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun permissionDenied() {
        showPermissionDeniedDialog()
    }

    @OnNeverAskAgain(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun neverAskAgain() {
        showPermissionDeniedDialog()
    }


    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("温馨提示")
            setMessage("你拒绝了权限请求，会影响应用更新等功能的正常使用！")
            setPositiveButton("去设置") { _, _ ->
                toAppDetail(0x99)
            }
            setNegativeButton("取消") { _, _ ->
                goToAdvertisingActivity()
            }
            setCancelable(false)
            setFinishOnTouchOutside(false)
        }.create().show()
    }
}
