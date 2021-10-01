package com.example.movie.ui.favoritesscreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoritesView:MvpView {
    fun onMovieCardClick(id: String)
    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal)
    fun updateFavorites(favoriteList: ArrayList<MovieDetailsLocal>)
}