package com.leeeyou.setting

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import com.leeeyou.R
import com.leeeyou.login.event.LogoutSuccessEvent
import com.leeeyou.login.model.logout
import com.leeeyou.manager.BaseActivity
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.T
import com.leeeyou.util.startBrowserActivity
import kotlinx.android.synthetic.main.activity_setting.*
import org.greenrobot.eventbus.EventBus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class SettingActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setLeftTitleAndDisplayHomeAsUp("设置")

        try {
            val info = packageManager.getPackageInfo(this.packageName, 0)
            tv_version.text = "Version " + info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        tv_login_out.setOnClickListener {
            val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@SettingActivity)
            val loginUser = defaultSharedPreferences.getString("loginUser", null)
            if (loginUser == null) {
                T.showShort(this@SettingActivity, "请先登录")
                return@setOnClickListener
            }
            val builder = AlertDialog.Builder(this@SettingActivity)
            builder.setMessage("确定要退出吗？")
                    .setNegativeButton("取消") { dialogInterface, _ -> dialogInterface.dismiss() }
                    .setPositiveButton("确定") { _, _ ->
                        logout().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : DefaultHttpResultSubscriber<String>() {
                                    override fun onSuccess(data: String?) {
                                        PreferenceManager.getDefaultSharedPreferences(this@SettingActivity).edit().putString("loginUser", null).apply()
                                        EventBus.getDefault().post(LogoutSuccessEvent())
                                        finish()
                                    }
                                })
                    }
            builder.create().show()
        }

        tv_about_author.setOnClickListener { startBrowserActivity(this@SettingActivity, "https://github.com/Leeeyou", "Leeeyou") }

    }
}
