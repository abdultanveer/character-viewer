package com.xfinity.characterviewer


import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

fun ImageView.loadImage(context: Context, url: String, @DrawableRes placeHolder: Int) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions()
                    .placeholder(placeHolder)
                    .error(placeHolder))
            .into(this)
}
