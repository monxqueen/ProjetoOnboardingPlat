package com.example.favorite.presentation.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoaderImpl() : ImageLoader {
    override fun loadImage(context: Context, imageUrl: String, imgView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
//            .placeholder(R.drawable.cast_1)
            .into(imgView)
    }
}