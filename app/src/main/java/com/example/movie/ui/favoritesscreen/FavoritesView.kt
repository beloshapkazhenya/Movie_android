package com.example.movie.ui.favoritesscreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoritesView:MvpView {
    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal)
    fun updateFavoritesList(items: List<MovieDetailsLocal>)
}