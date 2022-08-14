package com.uz.instaclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uz.instaclone.R
import com.uz.instaclone.manager.AuthManager
import com.uz.instaclone.manager.handler.AuthHandler
import com.uz.instaclone.utils.Extensions.toast
import kotlinx.android.synthetic.main.activity_log_in.*

/**
 * This is the login page. You can access here by email and password
 */
class LogInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logIn.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty())
                firebaseSignIn(email, password)
        }
        sign_up.setOnClickListener {
            startActivity(Intent(this@LogInActivity, RegistrationActivity::class.java))
            finish()
        }
    }

    private fun firebaseSignIn(email: String, password: String) {
        showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess(uid: String) {
                dissmisLoading()
                toast(getString(R.string.str_successfully))
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                dissmisLoading()
                toast(getString(R.string.str_log_in_failed))
            }

        })
    }
}