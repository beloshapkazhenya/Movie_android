package com.example.movie.ui.searchscreen

import com.example.movie.model.response.searchbytitle.Search
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SearchView : MvpView {

    fun openSearchResultActivity(
        searchList: ArrayList<Search>,
        searchValue: String
    )

    fun openFavorite()
    fun showLoader()
    fun hideLoader()
}