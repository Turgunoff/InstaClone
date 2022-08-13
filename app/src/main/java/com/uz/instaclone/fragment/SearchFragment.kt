package com.uz.instaclone.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uz.instaclone.R
import com.uz.instaclone.adapter.SearchAdapter
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
        refreshAdapter(loadUsers())
    }

    private fun usersByKeyword(keyword: String) {
        if (keyword.isEmpty())
            refreshAdapter(items)

        users.clear()
        for (user in items)
            if (user.fullName.toLowerCase().startsWith(keyword.toLowerCase()))
                users.add(user)

        refreshAdapter(users)

    }

    private fun loadUsers(): ArrayList<User> {
        items = ArrayList<User>()
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        items.add(User("Eldor", "eldor@gmail.com"))
        items.add(User("Farrux", "eldor@gmail.com"))
        items.add(User("Shoxrux", "eldor@gmail.com"))
        items.add(User("Elyor", "eldor@gmail.com"))
        return items
    }

    private fun refreshAdapter(items: ArrayList<User>) {
        val adapter = SearchAdapter(this, items)
        rv_search.adapter = adapter
    }
}