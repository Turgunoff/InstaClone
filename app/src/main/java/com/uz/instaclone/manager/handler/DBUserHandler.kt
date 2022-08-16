package com.uz.instaclone.manager.handler

import com.uz.instaclone.model.User

/**
 * Created by Eldor Turgunov on 16.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */

interface DBUserHandler {
    fun onSuccess(user: User? = null)
    fun onError(e: Exception?)
}