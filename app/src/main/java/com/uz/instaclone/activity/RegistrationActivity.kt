package com.uz.instaclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uz.instaclone.R

/**
 * This is the Registration page. Here you can register using FullName, Email and Password
 */
class RegistrationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }
}