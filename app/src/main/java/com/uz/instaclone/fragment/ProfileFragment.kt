package com.uz.instaclone.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.uz.instaclone.R
import com.uz.instaclone.adapter.ProfileAdapter
import com.uz.instaclone.manager.AuthManager
import com.uz.instaclone.manager.DBManager
import com.uz.instaclone.manager.StorageManager
import com.uz.instaclone.manager.handler.DBPostsHandler
import com.uz.instaclone.manager.handler.DBUserHandler
import com.uz.instaclone.manager.handler.DBUsersHandler
import com.uz.instaclone.manager.handler.StorageHandler
import com.uz.instaclone.model.Post
import com.uz.instaclone.model.User

/**
 * This is the Profile page, where profile settings can be made
 */
class ProfileFragment : BaseFragment() {
    val TAG = ProfileFragment::class.java.simpleName
    lateinit var rv_profile: RecyclerView
    lateinit var logOut: ImageView
    var allPhotos = ArrayList<Uri>()
    var pickedPhoto: Uri? = null
    lateinit var iv_profile: ImageView
    lateinit var tv_fullname: TextView
    lateinit var tv_email: TextView
    lateinit var tv_posts: TextView
    lateinit var tv_following: TextView
    lateinit var tv_followers: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        return view
    }


    private fun initViews(view: View) {
        rv_profile = view.findViewById(R.id.rv_profile)
        iv_profile = view.findViewById(R.id.iv_profile)
        tv_fullname = view.findViewById(R.id.tv_fullname)
        tv_email = view.findViewById(R.id.tv_email)
        tv_posts = view.findViewById(R.id.tv_posts)
        tv_following = view.findViewById(R.id.tv_following)
        tv_followers = view.findViewById(R.id.tv_followers)

        logOut = view.findViewById(R.id.logOut)
        rv_profile.layoutManager = GridLayoutManager(activity, 2)
        val iv_profile = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        iv_profile.setOnClickListener {
            pickFishBunPhoto()
        }
        logOut.setOnClickListener {
            AuthManager.signOut()
            callSignInActivity(requireActivity())
        }

        loadUserInfo()
        loadMyPosts()
        loadMyFollowing()
        loadMyFollowers()
    }

    private fun loadMyFollowing() {
        val uid = AuthManager.currentUser()!!.uid
        DBManager.loadFollowing(uid, object : DBUsersHandler {
            override fun onSuccess(users: ArrayList<User>) {
                tv_following.text = users.size.toString()
            }
            override fun onError(e: Exception) {
            }
        })
    }

    private fun loadMyFollowers() {
        val uid = AuthManager.currentUser()!!.uid
        DBManager.loadFollowers(uid, object : DBUsersHandler {
            override fun onSuccess(users: ArrayList<User>) {
                tv_followers.text = users.size.toString()
            }

            override fun onError(e: Exception) {
            }
        })
    }

    private fun loadMyPosts() {
        val uid = AuthManager.currentUser()!!.uid
        DBManager.loadPosts(uid, object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Post>) {
                tv_posts.text = posts.size.toString()
                refreshAdapter(posts)
            }

            override fun onError(e: java.lang.Exception) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun loadUserInfo() {
        DBManager.loadUser(AuthManager.currentUser()!!.uid, object : DBUserHandler {
            override fun onSuccess(user: User?) {
                if (user != null) {
                    showUserInfo(user)
                }
            }

            override fun onError(e: Exception?) {

            }
        })
    }

    private fun showUserInfo(user: User) {
        tv_fullname.text = user.fullname
        tv_email.text = user.email

        Glide.with(this).load(user.userImg)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(iv_profile)
    }

    private fun pickFishBunPhoto() {
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbum()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            FishBun.FISHBUN_REQUEST_CODE -> {
                allPhotos = data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
                uploadUserPhoto()
            }
        }
    }

    private fun uploadUserPhoto() {
        if (pickedPhoto == null) return

        StorageManager.uploadUserPhoto(pickedPhoto!!, object : StorageHandler {
            override fun onSuccess(imgUrl: String) {
                DBManager.updateUserImg(imgUrl)
                iv_profile.setImageURI(pickedPhoto)
            }

            override fun onError(exception: Exception?) {

            }
        })

    }

    private fun refreshAdapter(items: ArrayList<Post>) {
        val adapter = ProfileAdapter(this, items)
        rv_profile.adapter = adapter
    }
}