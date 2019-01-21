package com.leeeyou.skin

import android.os.Bundle
import com.leeeyou.R
import com.leeeyou.manager.BaseActivity

class ChangeSkinActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_skin)
        setLeftTitleAndDisplayHomeAsUp("个性换肤")
    }
}