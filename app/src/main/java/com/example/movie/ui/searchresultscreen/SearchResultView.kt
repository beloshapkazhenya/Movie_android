package com.example.movie.ui.searchresultscreen

import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.moviesearch.SearchItemResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SearchResultView: MvpView {
    fun openFavorite()
    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal)
    fun updateSearchResultList(items: List<SearchItemResponse>)
    fun showLoader()
    fun hideLoader()
    fun showMessage()
    fun updateSearchTitle(title: String)
}