package com.uz.instaclone.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uz.instaclone.R
import com.uz.instaclone.adapter.HomeAdapter
import com.uz.instaclone.model.Post

/**
 * This is the Home Page, where you and your friends will see posts
 */
class HomeFragment : BaseFragment() {
    lateinit var recyclerView: RecyclerView

    //    private var listener: HomeListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        return view

    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity, 1)

        val iv_camera = view.findViewById<ImageView>(R.id.iv_camera)
        iv_camera.setOnClickListener {
//            listener!!.scrollToUpload()
        }

        refreshAdapter()
    }

    private fun refreshAdapter() {
        val items = ArrayList<Post>()
        val adapter = HomeAdapter(this, items)
        recyclerView.adapter = adapter
        items.add(Post("https://images.unsplash.com/photo-1659519529276-a6a42aaa0b7b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDEyfENEd3V3WEpBYkV3fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1659259541374-22a6df2fee1d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDIxfENEd3V3WEpBYkV3fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8cGVyc29ufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        items.add(Post("https://images.unsplash.com/photo-1492681290082-e932832941e6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHBlcnNvbnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = if (context is HomeListener) {
//            context
//        } else {
//            throw RuntimeException("$context must implement FirstListener")
//        }
//    }

    /**
     * onDetach is for communication of Fragments
     */
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }
//
//    interface HomeListener {
//        fun scrollToUpload()
//    }
}