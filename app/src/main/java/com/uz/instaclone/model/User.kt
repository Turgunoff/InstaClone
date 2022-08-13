package com.uz.instaclone.model


/**
 * Created by Eldor Turgunov on 13.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
class User {
    var fullName: String = ""
    var email: String = ""

    constructor(fullName: String, email: String) {
        this.email = email
        this.fullName = fullName
    }
}