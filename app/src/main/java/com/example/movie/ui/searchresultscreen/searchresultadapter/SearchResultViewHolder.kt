package com.example.movie.ui.searchresultscreen.searchresultadapter

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.base.baseadapter.BaseViewHolder
import com.example.movie.model.response.moviesearch.SearchItemResponse
import io.reactivex.subjects.PublishSubject

class SearchResultViewHolder(
    view: View,
    private val itemClickSubject: PublishSubject<SearchItemResponse>
) :
    BaseViewHolder<SearchItemResponse>(view) {

    override fun bind(model: SearchItemResponse) {

        bindPoster(model.poster.toString())

        bindTitle(model.title)

        bindOnClickListener(model)

    }

    private fun bindOnClickListener(model: SearchItemResponse) {
        itemView
            .findViewById<LinearLayout>(R.id.vLnLtMovieCard)
            .setOnClickListener {
                itemClickSubject.onNext(model)
            }
    }

    private fun bindTitle(title: String?) {
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