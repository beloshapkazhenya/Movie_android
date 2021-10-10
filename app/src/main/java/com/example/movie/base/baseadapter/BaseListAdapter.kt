package com.example.movie.base.baseadapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(
    proposedItems: MutableList<T> = ArrayList()
) : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private val items: ArrayList<T> = ArrayList(proposedItems)


    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.indexOf(item))
    }

    private fun removeItem(position: Int): T {
        val item = items.removeAt(position)
        notifyItemRemoved(position)
        return item
    }

    private fun moveItem(fromPosition: Int, toPosition: Int) {
        val item = items.removeAt(fromPosition)
        if (toPosition >= items.size) {
            items.add(item)
        } else {
            items.add(toPosition, item)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    open fun setItems(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun getItemsList() = items

    open fun getItem(position: Int): T = items[position]

}