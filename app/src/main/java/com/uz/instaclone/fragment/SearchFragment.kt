package com.uz.instaclone.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uz.instaclone.R
import com.uz.instaclone.adapter.SearchAdapter
import com.uz.instaclone.manager.AuthManager
import com.uz.instaclone.manager.DBManager
import com.uz.instaclone.manager.handler.DBFollowHandler
import com.uz.instaclone.manager.handler.DBUserHandler
import com.uz.instaclone.manager.handler.DBUsersHandler
import com.uz.instaclone.model.User

/**
 * This is the Search page, where you can search
 */
class SearchFragment : BaseFragment() {
    lateinit var rv_search: RecyclerView
    private lateinit var et_search: EditText
    var items = ArrayList<User>()
    var users = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        rv_search = view.findViewById(R.id.rv_search)
        rv_search.layoutManager = GridLayoutManager(activity, 1)

        et_search = view.findViewById(R.id.et_search)
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = s.toString().trim()
                usersByKeyword(keyword)
            }
        })
        loadUsers()
    }

    private fun refreshAdapter(items: ArrayList<User>) {
        val adapter = SearchAdapter(this, items)
        rv_search.adapter = adapter
    }

    fun usersByKeyword(keyword: String) {
        if (keyword.isEmpty())
            refreshAdapter(items)

        users.clear()
        for (user in items)
            if (user.fullname.toLowerCase().startsWith(keyword.toLowerCase()))
                users.add(user)

        refreshAdapter(users)
    }

    private fun loadUsers() {
        val uid = AuthManager.currentUser()!!.uid
        DBManager.loadUsers(object : DBUsersHandler {
            override fun onSuccess(users: ArrayList<User>) {
                DBManager.loadFollowing(uid, object : DBUsersHandler {
                    override fun onSuccess(following: ArrayList<User>) {
                        items.clear()
                        items.addAll(mergedUsers(uid, users, following))
                        refreshAdapter(items)
                    }

                    override fun onError(e: Exception) {
                    }
                })
            }

            override fun onError(e: Exception) {
            }
        })
    }

    private fun mergedUsers(
        uid: String,
        users: ArrayList<User>,
        following: ArrayList<User>
    ): ArrayList<User> {
        val items = ArrayList<User>()
        for (u in users) {
            val user = u
            for (f in following) {
                if (u.uid == f.uid) {
                    user.isFollowed = true
                    break
                }
            }
            if (uid != user.uid) {
                items.add(user)
            }
        }
        return items
    }

    fun followOrUnFollow(to: User) {
        val uid = AuthManager.currentUser()!!.uid
        if (!to.isFollowed) {
            followUser(uid, to)
        } else {
            unFollowUser(uid, to)
        }
    }

    private fun followUser(uid: String, to: User) {
        DBManager.loadUser(uid, object : DBUserHandler {
            override fun onSuccess(me: User?) {
                DBManager.followUser(me!!, to, object : DBFollowHandler {
                    override fun onSuccess(isDone: Boolean) {
                        to.isFollowed = true
                        DBManager.storePostsToMyFeed(uid, to)
                    }

                    override fun onError(e: Exception) {

                    }

                })
            }

            override fun onError(e: Exception?) {

            }
        })
    }

    private fun unFollowUser(uid: String, to: User) {
        DBManager.loadUser(uid, object : DBUserHandler {
            override fun onSuccess(me: User?) {
                DBManager.unFollowUser(me!!, to, object : DBFollowHandler {
                    override fun onSuccess(isDone: Boolean) {
                        to.isFollowed = false
                        DBManager.removePostsFromMyFeed(uid, to)
                    }

                    override fun onError(e: Exception) {
                    }

                })
            }

            override fun onError(e: Exception?) {

            }
        })
    }
}