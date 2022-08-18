package com.uz.instaclone.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uz.instaclone.R
import com.uz.instaclone.adapter.HomeAdapter
import com.uz.instaclone.manager.AuthManager
import com.uz.instaclone.manager.DBManager
import com.uz.instaclone.manager.handler.DBPostsHandler
import com.uz.instaclone.model.Post
import java.lang.Exception

/**
 * This is the Home Page, where you and your friends will see posts
 */
class HomeFragment : BaseFragment() {
    lateinit var recyclerView: RecyclerView
    var feeds: ArrayList<Post> = ArrayList()
    private var listener: HomeListener? = null
    lateinit var iv_camera: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        return view

    }

    @Deprecated("Deprecated in Java")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser && feeds.size > 0)
            loadMyFeeds()
    }

    private fun initViews(view: View) {
        iv_camera = view.findViewById(R.id.iv_camera)
        iv_camera.setOnClickListener {
            listener!!.scrollToUpload()
        }
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity, 1)

        loadMyFeeds()
    }

    private fun loadMyFeeds() {
        showLoading(requireActivity())
        val uid = AuthManager.currentUser()!!.uid
        DBManager.loadFeeds(uid, object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Post>) {
                dismissLoading()
                feeds.clear()
                feeds.addAll(posts)
                refreshAdapter(feeds)
            }

            override fun onError(e: Exception) {
                TODO("Not yet implemented")
            }

        })
    }

    fun refreshAdapter(items: ArrayList<Post>) {
        val adapter = HomeAdapter(this, items)
        recyclerView.adapter = adapter
    }

    interface HomeListener {
        fun scrollToUpload()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is HomeListener) {
            context
        } else {
            throw RuntimeException("$context must implement FirstListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}