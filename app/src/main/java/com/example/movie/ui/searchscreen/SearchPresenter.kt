package com.example.movie.ui.searchscreen

import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class SearchPresenter : MvpPresenter<SearchView>() {

    fun searchMovie(searchValue: String) {
        viewState.openSearchResultActivity(searchValue)
    }

    fun openFavorite() {
        viewState.openFavorite()
    }
}