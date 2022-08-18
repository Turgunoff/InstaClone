package com.uz.instaclone.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.uz.instaclone.R
import com.uz.instaclone.fragment.SearchFragment
import com.uz.instaclone.model.User


/**
 * Created by Eldor Turgunov on 13.08.2022.
 * Insta Clone
 * eldorturgunov777@gmail.com
 */
class SearchAdapter(var fragment: SearchFragment, var items: ArrayList<User>) : BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_search, parent, false)
        return UserViewHolder(view)
    }

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile: ShapeableImageView
        var tv_fullname: TextView
        var tv_email: TextView
        var tv_follow: TextView

        init {
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
            tv_email = view.findViewById(R.id.tv_email)
            tv_follow = view.findViewById(R.id.tv_follow)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user: User = items[position]
        if (holder is UserViewHolder) {
            holder.tv_fullname.text = user.fullname
            holder.tv_email.text = user.email
            Glide.with(fragment).load(user.userImg)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(holder.iv_profile)

            val tv_follow = holder.tv_follow
            tv_follow.setOnClickListener {
                if (!user.isFollowed) {
                    tv_follow.text = fragment.getString(R.string.str_following)
                    tv_follow.setTextColor(Color.BLACK)
                    tv_follow.setBackgroundResource(R.drawable.following_click)
                } else {
                    tv_follow.text = fragment.getString(R.string.str_follow)
                    tv_follow.setTextColor(Color.WHITE)
                    tv_follow.setBackgroundResource(R.drawable.textview_rounder_corners)
                }
                fragment.followOrUnFollow(user)
            }
            if (!user.isFollowed) {
                tv_follow.text = fragment.getString(R.string.str_follow)
                tv_follow.setTextColor(Color.WHITE)
                tv_follow.setBackgroundResource(R.drawable.textview_rounder_corners)
            } else {
                tv_follow.text = fragment.getString(R.string.str_following)
                tv_follow.setTextColor(Color.BLACK)
                tv_follow.setBackgroundResource(R.drawable.following_click)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}