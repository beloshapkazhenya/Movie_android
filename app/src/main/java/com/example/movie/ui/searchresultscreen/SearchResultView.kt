package com.example.movie.ui.searchresultscreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SearchResultView: MvpView {
    fun openFavorite()
    fun updateSearchTitle()
    fun updateSearchResult()
    fun onMovieCardClick(imdbID: String)
    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal)
}