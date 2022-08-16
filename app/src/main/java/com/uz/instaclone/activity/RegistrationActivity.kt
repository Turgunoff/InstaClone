package com.uz.instaclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uz.instaclone.R
import com.uz.instaclone.manager.AuthManager
import com.uz.instaclone.manager.DBManager
import com.uz.instaclone.manager.handler.AuthHandler
import com.uz.instaclone.manager.handler.DBUserHandler
import com.uz.instaclone.model.User
import com.uz.instaclone.utils.Extensions.toast
import kotlinx.android.synthetic.main.activity_registration.*

/**
 * This is the Registration page. Here you can register using FullName, Email and Password
 */
class RegistrationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registration.setOnClickListener {
            val fullName = et_fullName.text.toString().trim()
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(fullName, email, password, "")
                firebaseSignUp(user)
            }
        }
        sign_up.setOnClickListener {
            callLogInActivity(context)
        }
    }

    private fun firebaseSignUp(user: User) {
        showLoading(this)
        AuthManager.signUp(user.email, user.password, object : AuthHandler {
            override fun onSuccess(uid: String) {
                user.uid = uid
                toast(getString(R.string.str_registration_success))
                storeUserToDB(user)
            }

            override fun onError(exception: Exception?) {
                dissmisLoading()
                toast(getString(R.string.str_registration_failed))
            }

        })
    }

    private fun storeUserToDB(user: User) {
        DBManager.storeUser(user, object : DBUserHandler {

            override fun onSuccess(user: User?) {
                dissmisLoading()
                callMainActivity(context)
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }
        })
    }
}