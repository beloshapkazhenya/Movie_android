package com.example.movie.ui.favoritesscreen.favoritesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.R
import com.example.movie.base.baseadapter.BaseListAdapter
import com.example.movie.model.local.MovieDetailsLocal
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FavoritesAdapter(
    favoriteList: MutableList<MovieDetailsLocal>
) : BaseListAdapter<MovieDetailsLocal>(favoriteList) {

    private val itemClickSubject = PublishSubject.create<MovieDetailsLocal>()
    val itemClickObservable: Observable<MovieDetailsLocal> = itemClickSubject

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesViewHolder {

        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_card, parent, false)

        return FavoritesViewHolder(itemView, itemClickSubject)
    }
}