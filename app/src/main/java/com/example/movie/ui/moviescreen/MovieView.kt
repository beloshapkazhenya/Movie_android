package com.example.movie.ui.moviescreen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MovieView : MvpView {
    fun updatePlot(plot: String)
    fun updateActors(actors: String)
    fun updateCountry(country: String)
    fun updateWriter(writer: String)
    fun updateDirector(director: String)
    fun updateGenre(genre: String)
    fun updateRuntime(runtime: String)
    fun updateReleased(released: String)
    fun updateMoviePoster(poster: String)
    fun updateTitle(title: String)
    fun openFavorite()
    fun addToFavorite()
    fun makeAddToFavoriteButtonUnavailable()
    fun makeAddToFavoriteButtonAvailable()
}