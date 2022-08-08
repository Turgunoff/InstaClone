package com.uz.instaclone.utils

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import com.uz.instaclone.model.ScreenSize

object Utils {
    fun fireToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun screenSize(context: Application): ScreenSize {
        val displayMatrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMatrics)
        val deviceWidht = displayMatrics.widthPixels
        val deviceHeight = displayMatrics.heightPixels
        return ScreenSize(deviceWidht, deviceHeight)

    }
}