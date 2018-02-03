package com.xfinity.characterviewer.adapter.character

import android.content.Context
import android.support.annotation.DrawableRes
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.adapter.BaseAdapter
import com.xfinity.characterviewer.adapter.BaseViewHolder
import com.xfinity.characterviewer.adapter.OnItemClickListener
import com.xfinity.characterviewer.models.Character

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

class CharacterAdapter(context: Context,
                       dataList: List<Character>?,
                       listener: OnItemClickListener<CharacterAdapter.ViewHolder, Character>?) :
        BaseAdapter<Character, CharacterAdapter.ViewHolder>(context, dataList, listener) {


    override fun createViewHolder(parent: ViewGroup): ViewHolder =
            ViewHolder(inflate(R.layout.list_item_character, parent))

    override fun bindViewHolder(holder: ViewHolder, item: Character?) {
        item?.let { it0 ->
            with(holder) {
                title.text = it0.title()
                favorite.setImageResource(getFavoriteRes(it0.isFavorite))
                favorite.setOnClickListener { setFavoriteStatus(it0, holder.adapterPosition) }

                //Each view requires unique transition name, used adapter position
                title.setTransitionName("title_${holder.adapterPosition}")
            }
        }
    }

    @DrawableRes
    private fun getFavoriteRes(isFavorite: Boolean): Int =
            if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border

    private fun setFavoriteStatus(item: Character, position: Int) {
        item.isFavorite = !item.isFavorite
        notifyItemChanged(position)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvtitle)
        val favorite: ImageView = itemView.findViewById(R.id.favorite)
    }

}