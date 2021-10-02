package com.example.movie.base.baseadapter


import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import kotlinx.android.extensions.LayoutContainer

/**
 * Base class for all view holders to be used in concrete implementations of [BaseListAdapter]
 */
abstract class BaseViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    abstract fun bind(model: T)

    override val containerView: View?
        get() = itemView

    val context: Context
        get() = itemView.context

    fun setPositionInList(position: Int) {
        itemView.setBackgroundColor(
            if ((position % 2) == 0) Color.WHITE else ContextCompat.getColor(
                itemView.context,
                R.color.black
            )
        )
    }

    fun getColor(@ColorRes color: Int) = ContextCompat.getColor(itemView.context, color)
}