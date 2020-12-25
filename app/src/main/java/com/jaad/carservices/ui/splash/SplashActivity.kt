package com.jaad.carservices.ui.splash

import android.content.Intent
import android.os.CountDownTimer
import com.jaad.carservices.R
import com.jaad.carservices.ui.MainActivity
import com.jaad.carservices.ui.base.BaseActivity
import java.util.*

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val layoutRes = R.layout.activity_splash

    override fun setUp() {

        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                decideNextActivity()
            }
        }.start()
    }


    private fun decideNextActivity() {
        startActivity(
            Intent(
                this@SplashActivity,
                MainActivity::class.java
            )
        )
        finish()
    }

}