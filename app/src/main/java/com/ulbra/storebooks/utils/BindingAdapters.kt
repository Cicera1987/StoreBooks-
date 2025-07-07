package com.ulbra.storebooks.utils

import com.bumptech.glide.Glide
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ulbra.storebooks.R


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(view)
    } else {
        view.setImageResource(R.drawable.ic_placeholder)
    }
}
