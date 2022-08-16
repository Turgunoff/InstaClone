package com.uz.instaclone.manager.handler


/**
 * Created by Eldor Turgunov on 16.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
interface StorageHandler {
    fun onSuccess(imgUrl: String)
    fun onError(exception: Exception?)
}