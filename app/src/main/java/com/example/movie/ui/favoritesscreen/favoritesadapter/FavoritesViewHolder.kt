package com.example.movie.ui.favoritesscreen.favoritesadapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R

class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val poster: ImageView = itemView
        .findViewById(R.id.vImgVwMovieCardPoster)

    val movieTitle: TextView = itemView
        .findViewById(R.id.vTvMovieCardTitle)

    val movieCard: LinearLayout = itemView
        .findViewById(R.id.vLnLtMovieCard)

}