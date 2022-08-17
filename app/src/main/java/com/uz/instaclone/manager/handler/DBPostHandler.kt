package com.uz.instaclone.manager.handler

import com.uz.instaclone.model.Post


/**
 * Created by Eldor Turgunov on 17.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
interface DBPostHandler {
    fun onSuccess(post: Post)
    fun onError(e: Exception)
}