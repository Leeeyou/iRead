package com.leeeyou.login

import android.os.Bundle
import android.text.TextUtils
import com.leeeyou.R
import com.leeeyou.login.model.bean.User
import com.leeeyou.login.model.postRegister
import com.leeeyou.manager.BaseActivity
import com.leeeyou.service.subscriber.DefaultHttpResultSubscriber
import com.leeeyou.util.T
import kotlinx.android.synthetic.main.activity_reg.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

class RegActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        setLeftTitleAndDisplayHomeAsUp("注册")

        btn_goto_reg.setOnClickListener {
            if (TextUtils.isEmpty(et_username.text)) {
                T.showShort(this@RegActivity, "请输入用户名")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(et_password.text)) {
                T.showShort(this@RegActivity, "请输入密码")
                return@setOnClickListener
            }

            postRegister(et_username.text.toString(), et_password.text.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DefaultHttpResultSubscriber<User>() {
                        override fun onSuccess(data: User?) {
                            Timber.e(data.toString())
                            T.showShort(this@RegActivity, "注册成功")
                            finish()
                        }

                        override fun _onError(status: Int, msg: String?) {
                            T.showShort(this@RegActivity, msg)
                        }
                    })
        }
    }
}
