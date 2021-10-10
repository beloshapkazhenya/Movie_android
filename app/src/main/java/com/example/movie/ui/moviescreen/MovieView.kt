package com.example.movie.ui.moviescreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MovieView : MvpView {
    fun openFavorite()
    fun actionMovieIsInFavoriteList()
    fun actionMovieIsNotInFavoriteList()
    fun updateUI(movieDetailsLocal: MovieDetailsLocal)
}