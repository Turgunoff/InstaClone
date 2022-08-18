package com.uz.instaclone.fragment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.uz.instaclone.R
import com.uz.instaclone.activity.LogInActivity

/**
 * This is the Base Fragment, the parent of all fragments
 */

open class BaseFragment : Fragment() {
    var progressDialog: AlertDialog? = null

    fun callSignInActivity(activity: Activity) {
        val intent = Intent(context, LogInActivity::class.java)
        startActivity(intent)
        activity.finish()
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

    protected fun dismissLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}