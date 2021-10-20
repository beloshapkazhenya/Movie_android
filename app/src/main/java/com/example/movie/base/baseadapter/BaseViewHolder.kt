package com.example.movie.base.baseadapter


import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    abstract fun bind(model: T)

    override val containerView: View?
        get() = itemView

    val context: Context
        get() = itemView.context
}