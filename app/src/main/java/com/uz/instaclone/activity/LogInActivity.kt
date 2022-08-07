package com.uz.instaclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uz.instaclone.R
import kotlinx.android.synthetic.main.activity_log_in.*

/**
 * This is the login page. You can access here by email and password
 */
class LogInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logIn.setOnClickListener {
            startActivity(Intent(this@LogInActivity, MainActivity::class.java))
            finish()
        }
        sign_up.setOnClickListener {
            startActivity(Intent(this@LogInActivity, RegistrationActivity::class.java))
            finish()
        }
    }
}