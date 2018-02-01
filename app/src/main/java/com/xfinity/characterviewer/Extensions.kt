package com.xfinity.characterviewer


import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
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

fun FragmentManager.replace(@IdRes container: Int, fragment: Fragment, name: String, addStack: Boolean = true) {
    this.beginTransaction()
            .replace(container, fragment, name)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).run {
        if (addStack) this.addToBackStack(name)
        this.commit()
    }
}

fun View.visible(isVisible: Boolean = true) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}