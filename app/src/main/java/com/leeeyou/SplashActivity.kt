package com.leeeyou

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        StatusBarUtil.setTransparent(this)
        lottieAnimationView.playAnimation()
        lottieAnimationView.speed = 1.5F
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                tv_appname.visibility = View.VISIBLE
                startActivity(Intent(this@SplashActivity, IndexActivity::class.java))
                finish()
            }
        })
    }
}