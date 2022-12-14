package com.uz.instaclone.model


/**
 * Created by Eldor Turgunov on 13.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
class User {
    var uid: String = ""
    var fullname: String = ""
    var email: String = ""
    var password: String = ""
    var userImg: String = ""

    var isFollowed: Boolean = false

    constructor(fullName: String, email: String) {
        this.email = email
        this.fullname = fullName
    }

    constructor(fullName: String, email: String, userImg: String) {
        this.email = email
        this.fullname = fullName
        this.userImg = userImg
    }

    constructor(fullName: String, email: String, password: String, userImg: String) {
        this.email = email
        this.fullname = fullName
        this.password = password
        this.userImg = userImg
    }
}