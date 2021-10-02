package com.example.movie.ui.favoritesscreen

import com.example.movie.App
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.repository.FavoritesStorage
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.instance

@InjectViewState
class FavoritesPresenter : MvpPresenter<FavoritesView>() {

    private val favoritesStorage: FavoritesStorage by App.kodein.instance()

    fun openMovieDetails(id: String) {
        getMovieDetails(id)?.let { viewState.openMovieActivity(it) }
    }

    fun updateFavoritesActivity() {
        viewState.updateFavorites(getFavoriteList())
    }

    private fun getMovieDetails(id: String): MovieDetailsLocal? {

        return favoritesStorage.getMovie(id)
    }

    private fun getFavoriteList(): MutableList<MovieDetailsLocal> {
        return favoritesStorage.getFavoritesList()
    }

}