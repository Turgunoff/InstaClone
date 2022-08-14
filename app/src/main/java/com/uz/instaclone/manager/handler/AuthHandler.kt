package com.uz.instaclone.manager.handler

interface AuthHandler {
    fun onSuccess(uid: String)
    fun onError(exception: Exception?)
}