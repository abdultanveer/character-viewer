package com.xfinity.characterviewer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var clickListener: ViewHolderClickListener? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun setListener(listener: ViewHolderClickListener) {
        clickListener = listener
    }

    override fun onClick(view: View?) {
      clickListener?.onClick(this)
    }
}
