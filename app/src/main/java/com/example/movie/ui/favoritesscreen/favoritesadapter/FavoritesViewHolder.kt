package com.example.movie.ui.favoritesscreen.favoritesadapter

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.base.baseadapter.BaseViewHolder
import com.example.movie.model.local.MovieDetailsLocal
import io.reactivex.subjects.PublishSubject

class FavoritesViewHolder(
    view: View,
    private val itemClickSubject: PublishSubject<MovieDetailsLocal>
) : BaseViewHolder<MovieDetailsLocal>(view) {


    override fun bind(model: MovieDetailsLocal) {
        bindPoster(model.poster.toString())
        bindTitle(model.title.toString())
        bindOnClickListener(model)
    }

    private fun bindOnClickListener(model: MovieDetailsLocal) {
        itemView
            .findViewById<LinearLayout>(R.id.vLnLtMovieCard)
            .setOnClickListener {
                itemClickSubject.onNext(model)
            }
    }

    private fun bindTitle(title: String) {
        itemView
            .findViewById<TextView>(R.id.vTvMovieCardTitle)
            .text = title
    }

    private fun bindPoster(poster: String) {

        Glide
            .with(context)
            .load(poster)
            .placeholder(R.drawable.movie_poster_placeholder)
            .error(R.drawable.movie_poster_placeholder)
            .into(
                itemView
                    .findViewById(R.id.vImgVwMovieCardPoster)
            )
    }

}