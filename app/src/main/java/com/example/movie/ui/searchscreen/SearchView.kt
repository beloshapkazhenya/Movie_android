package com.example.movie.ui.searchscreen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SearchView : MvpView {
    fun openSearchResultActivity(searchValue: String)
    fun openFavorite()
}