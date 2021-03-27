package com.iti.elfarsisy.mad41.newsly.features.details.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.iti.elfarsisy.mad41.myapplication.util.MyApplication
import com.squareup.picasso.Picasso

@BindingAdapter("articleImage")
fun bindArticleImage(imageView: ImageView, imageUrl: String) {
    imageUrl?.let {
        Glide.with(imageView.context).load(imageUrl)
            .into(imageView)
    }
}