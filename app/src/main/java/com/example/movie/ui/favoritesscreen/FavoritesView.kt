package com.example.movie.ui.favoritesscreen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoritesView:MvpView {
    fun onMovieCardClick(id: String)
    fun openMovieActivity(id:String)
}