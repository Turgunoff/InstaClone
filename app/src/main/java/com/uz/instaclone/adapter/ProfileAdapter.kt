package com.uz.instaclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.uz.instaclone.R
import com.uz.instaclone.fragment.ProfileFragment
import com.uz.instaclone.model.Post
import com.uz.instaclone.utils.Utils

class ProfileAdapter(var fragment: ProfileFragment, var items: ArrayList<Post>) : BaseAdapter() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_profile, parent, false)
        return PostViewHolder(view)
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_post: ShapeableImageView
        var tv_caption: TextView

        init {
            iv_post = view.findViewById(R.id.iv_post)
            tv_caption = view.findViewById(R.id.tv_caption)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = items[position]

        if (holder is PostViewHolder) {
            val iv_post = holder.iv_post
            setViewHeight(iv_post)
            Glide.with(fragment).load(post.image).into(iv_post)
        }
    }

    private fun setViewHeight(view:View){
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.height = Utils.screenSize(fragment.requireActivity().application).width/2
        view.layoutParams = params
    }
}