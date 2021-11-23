package com.example.favorite.presentation.utils

import android.content.Context
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(context: Context, imageUrl: String, imgView: ImageView)
}