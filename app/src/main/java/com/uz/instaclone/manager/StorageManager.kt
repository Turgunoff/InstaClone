package com.uz.instaclone.manager

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.uz.instaclone.manager.handler.StorageHandler


/**
 * Created by Eldor Turgunov on 16.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
private var USER_PHOTO_PATH = "users"
private var POST_PHOTO_PATH = "posts"

object StorageManager {
    private val storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference

    fun uploadPostPhoto(uri: Uri, handler: StorageHandler) {
        val filename =
            AuthManager.currentUser()!!.uid + "_" + System.currentTimeMillis().toString() + ".png"
        val uploadTask: UploadTask = storageRef.child(POST_PHOTO_PATH).child(filename).putFile(uri)
        uploadTask.addOnSuccessListener {
            val result = it.metadata!!.reference!!.downloadUrl;
            result.addOnSuccessListener {
                val imgUrl = it.toString()
                handler.onSuccess(imgUrl)
            }.addOnFailureListener { e ->
                handler.onError(e)
            }
        }.addOnFailureListener { e ->
            handler.onError(e)
        }
    }

    fun uploadUserPhoto(uri: Uri, handler: StorageHandler) {
        val filename = AuthManager.currentUser()!!.uid + ".png"
        val uploadTask: UploadTask = storageRef.child(USER_PHOTO_PATH).child(filename).putFile(uri)
        uploadTask.addOnSuccessListener {
            val result = it.metadata!!.reference!!.downloadUrl;
            result.addOnSuccessListener {
                val imgUrl = it.toString()
                handler.onSuccess(imgUrl)
            }.addOnFailureListener { e ->
                handler.onError(e)
            }
        }.addOnFailureListener { e ->
            handler.onError(e)
        }
    }
}