package com.uz.instaclone.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.uz.instaclone.R

/**
 * It's Base Activity
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var context: Context
    var progressDialog: AlertDialog? = null
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        context = this
    }

    fun showLoading(activity: Activity?) {
        if (activity == null) return

        if (progressDialog != null && progressDialog!!.isShowing) {
        } else {
            val builder = AlertDialog.Builder(activity)
            val inflater: LayoutInflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.custom_progress_dialog, null))
            builder.setCancelable(true)
            progressDialog = builder.create()
            progressDialog!!.show()
        }
    }


    protected fun dissmisLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    fun callMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callRegistrationActivity(context: Context) {
        val intent = Intent(context, RegistrationActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callLogInActivity(context: Context) {
        val intent = Intent(context, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }


}