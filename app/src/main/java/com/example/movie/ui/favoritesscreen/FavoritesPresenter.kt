package com.example.movie.ui.favoritesscreen

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FavoritesPresenter : MvpPresenter<FavoritesView>() {

    fun openMovieDetails(id: String) {
        viewState.openMovieActivity(id)
    }

}