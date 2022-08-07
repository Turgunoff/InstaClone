package com.uz.instaclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import com.uz.instaclone.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(l: Long) {}
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, LogInActivity::class.java))
                finish()
//                if (firebaseUser == null) {
//                    callMainActivity(context)
//                } else {
//                    callLogInActivity(context)
//                }
            }
        }.start()
    }
}