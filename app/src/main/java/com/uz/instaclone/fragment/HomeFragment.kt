package com.uz.instaclone.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.uz.instaclone.R

/**
 * This is the Home Page, where you and your friends will see posts
 */
class HomeFragment : BaseFragment() {
    private var listener: HomeListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(view)
        return view

    }

    private fun initViews(view: View?) {
        val iv_camera = view?.findViewById<ImageView>(R.id.iv_camera)
        iv_camera?.setOnClickListener {
            listener!!.scrollToUpload()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is HomeListener) {
            context
        } else {
            throw RuntimeException("$context must implement FirstListener")
        }
    }

    /**
     * onDetach is for communication of Fragments
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface HomeListener {
        fun scrollToUpload()
    }
}