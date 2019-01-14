package com.leeeyou.login

import android.content.Intent
import android.os.Bundle

import com.leeeyou.R
import com.leeeyou.manager.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setLeftTitleAndDisplayHomeAsUp("登录")

        tv_goto_reg.setOnClickListener { startActivity(Intent(this@LoginActivity, RegActivity::class.java)) }
    }
}