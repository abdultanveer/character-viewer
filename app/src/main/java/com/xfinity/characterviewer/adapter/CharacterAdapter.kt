package com.xfinity.characterviewer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.models.Character

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class CharacterAdapter(private val context: Context,
                       private val list: List<Character>,
                       private val invoke: (Character) -> Unit) :
        RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_character, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(list[position]) {
            holder.favorite.setImageResource(
                    if (this.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
            holder.title.text = this.title()
            holder.itemView.setOnClickListener { invoke(this) }
            holder.favorite.setOnClickListener {
                this.isFavorite = !this.isFavorite
                notifyItemChanged(position)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvtitle)
        val favorite: ImageView = itemView.findViewById(R.id.favorite)
    }

    fun filterContains(typed: String) {

    }
}