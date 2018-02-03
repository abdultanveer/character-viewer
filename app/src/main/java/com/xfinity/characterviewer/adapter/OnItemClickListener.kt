package com.xfinity.characterviewer.adapter

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

interface OnItemClickListener<in VH : BaseViewHolder, T> {
    fun onItemClick(viewholder: VH?, item: T)
}