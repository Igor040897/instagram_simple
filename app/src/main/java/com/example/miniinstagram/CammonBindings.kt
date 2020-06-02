package com.example.miniinstagram

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUri", "placeholder"], requireAll = false)
fun AppCompatImageView.setImage(imageUri: Uri?, @DrawableRes placeholder: Int? = R.drawable.ic_image_black_48dp) {
    val options = RequestOptions()
        .apply {
            if (placeholder != null) placeholder(placeholder)
        }


    Glide.with(this)
        .load(imageUri)
        .apply(options)
        .into(this)
}

@BindingAdapter(value = ["imageWithCircleCropUri", "placeholder"], requireAll = false)
fun ImageView.setImageWithCircleCrop(imageWithCircleCropUri: Uri?, @DrawableRes placeholder: Int? = R.drawable.ic_image_black_48dp) {
    val options = RequestOptions()
        .apply {
            if (placeholder != null) placeholder(placeholder)
        }
        .circleCrop()

    Glide.with(this)
        .load(imageWithCircleCropUri)
        .apply(options)
        .into(this)
}