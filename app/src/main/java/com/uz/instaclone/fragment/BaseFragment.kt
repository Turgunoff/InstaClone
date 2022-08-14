package com.uz.instaclone.fragment

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.uz.instaclone.activity.LogInActivity

/**
 * This is the Base Fragment, the parent of all fragments
 */

open class BaseFragment : Fragment() {

    fun callSignInActivity(activity: Activity) {
        val intent = Intent(context, LogInActivity::class.java)
        startActivity(intent)
        activity.finish()
    }
}