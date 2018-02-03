package com.xfinity.characterviewer.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Mert Vurgun on 2/1/2018.
 */

abstract class BaseAdapter<M, VH : BaseViewHolder>(val context: Context,
                                                   dataList: List<M>? = null,
                                                   listener: OnItemClickListener<VH, M>? = null) : RecyclerView.Adapter<BaseViewHolder>(), ViewHolderClickListener {

    private var items: MutableList<M>? = dataList?.toMutableList()
    private val listener: OnItemClickListener<VH, M>? = listener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        with(createViewHolder(parent)) {
            setListener(this@BaseAdapter)
            return this
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        holder?.let { bindViewHolder(it as VH, getItem(position)) }
    }


    override fun getItemCount(): Int = items?.size ?: 0

    protected fun getItem(position: Int): M? {

        items?.let {
            return if (position < 0 || position >= it.size) null else it[position]
        } ?: return null
    }

    fun setItems(items: List<M>?) {
        this.items = items?.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItem(item: M, position: Int) {
        items?.let {
            if (it.size > position) {
                it[position] = item
                notifyItemChanged(position)
            }
        }
    }

    override fun onClick(viewHolder: RecyclerView.ViewHolder) {

        getItem(viewHolder.adapterPosition)?.let {
            listener?.onItemClick(viewHolder as VH, it)
        } ?: return
    }


    //Correct holder type
    protected abstract fun bindViewHolder(holder: VH, item: M?)

    //Child true holder
    protected abstract fun createViewHolder(parent: ViewGroup): VH


    protected fun inflate(@LayoutRes resId: Int, parent: ViewGroup): View =
            LayoutInflater.from(context).inflate(resId, parent, false)

}