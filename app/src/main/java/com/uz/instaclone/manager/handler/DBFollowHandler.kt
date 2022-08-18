package com.uz.instaclone.manager.handler


/**
 * Created by Eldor Turgunov on 18.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
interface DBFollowHandler {
    fun onSuccess(isDone: Boolean)
    fun onError(e: Exception)
}