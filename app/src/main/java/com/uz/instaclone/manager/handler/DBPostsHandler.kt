package com.uz.instaclone.manager.handler

import com.uz.instaclone.model.Post
import java.lang.Exception

interface DBPostsHandler {
    fun onSuccess(posts: ArrayList<Post>)
    fun onError(e: Exception)

}
