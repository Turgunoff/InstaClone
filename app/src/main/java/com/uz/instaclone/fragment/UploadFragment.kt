package com.uz.instaclone.fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.uz.instaclone.R
import com.uz.instaclone.utils.Utils
import kotlinx.android.synthetic.main.fragment_upload.*


/**
 * This is the Upload page, where you can upload images and videos to your page
 */
class UploadFragment : BaseFragment() {

    lateinit var fl_photo: FrameLayout
    lateinit var iv_photo: ImageView
    lateinit var et_caption: EditText
//    private var listener: UploadListener? = null

//    var pickedPhoto: Uri? = null
//    var allPhotos = ArrayList<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload, container, false)
        initViews(view)
        return view
    }
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = if (context is UploadListener) {
//            context
//        } else {
//            throw RuntimeException("$context must implement FirstListener")
//        }
//    }
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }


    private fun initViews(view: View) {
        val fl_view = view.findViewById<FrameLayout>(R.id.fl_view)
//        setViewHeight(fl_view)
        et_caption = view.findViewById(R.id.et_caption)
        fl_photo = view.findViewById(R.id.fl_photo)
        iv_photo = view.findViewById(R.id.iv_photo)
        val iv_close = view.findViewById<ImageView>(R.id.iv_close)
        val iv_pick = view.findViewById<ImageView>(R.id.iv_pick)
        val iv_upload = view.findViewById<ImageView>(R.id.iv_upload)

        iv_pick.setOnClickListener {
//            pickFishBunPhoto()
        }
        iv_close.setOnClickListener {
//            hidePickedPhoto()
        }
        iv_upload.setOnClickListener {
//            uploadNewPost()
        }
    }

    private fun setViewHeight(view: View) {
        val params: ViewGroup.LayoutParams = view.getLayoutParams()
        params.height = Utils.screenSize(requireActivity().application).width
        view.setLayoutParams(params)
    }

//    private fun pickFishBunPhoto() {
//        FishBun.with(this@UploadFragment)
//            .setImageAdapter(GlideAdapter())
//            .setMaxCount(1)
//            .setMinCount(1)
//            .setSelectedImages(allPhotos)
//    }
//    private val photoLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                allPhotos =
//                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
//                pickedPhoto = allPhotos.get(0)
//                showPickedPhoto()
//            }
//        }

//    private fun uploadNewPost() {
//        val caption = et_caption.text.toString().trim()
//        if (caption.isNotEmpty() && pickedPhoto != null) {
//            listener!!.scrollToHome()
//            et_caption.text.clear()
//        }
//    }

//    private fun showPickedPhoto() {
//        fl_photo.visibility = View.VISIBLE
//        iv_photo.setImageURI(pickedPhoto)
//    }

//    private fun hidePickedPhoto() {
//        pickedPhoto = null
//        fl_photo.visibility = View.GONE
//    }
//    interface UploadListener {
//        fun scrollToHome()
//    }
}