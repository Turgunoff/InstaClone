package com.uz.instaclone.manager.handler

import com.uz.instaclone.model.User

/**
 * Created by Eldor Turgunov on 16.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */

interface DBUsersHandler {
    fun onSuccess(users: ArrayList<User>)
    fun onError(e: Exception)
}