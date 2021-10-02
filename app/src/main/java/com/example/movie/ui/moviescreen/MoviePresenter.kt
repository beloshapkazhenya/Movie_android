package com.example.movie.ui.moviescreen

import com.example.movie.App
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.repository.FavoritesStorage
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.instance

@InjectViewState
class MoviePresenter : MvpPresenter<MovieView>() {

    private val favoritesStorage: FavoritesStorage by App.kodein.instance()

    fun updateMovieDetails(movieDetails: MovieDetailsLocal) {

        viewState.run {
            updateTitle(movieDetails.title.toString())
            updateMoviePoster(movieDetails.poster.toString())
            updateReleased(movieDetails.released.toString())
            updateRuntime(movieDetails.runtime.toString())
            updateGenre(movieDetails.genre.toString())
            updateDirector(movieDetails.director.toString())
            updateWriter(movieDetails.writer.toString())
            updateCountry(movieDetails.country.toString())
            updateActors(movieDetails.actors.toString())
            updatePlot(movieDetails.plot.toString())
        }

    }

    private fun checkMovieInFavoriteList(isInFavorite: Boolean) {

        when (isInFavorite) {
            true -> viewState.actionMovieIsNotInFavoriteList()
            false -> viewState.actionMovieIsInFavoriteList()
        }

    }

    fun checkMovieInFavoriteList(movieDetails: MovieDetailsLocal) {
        checkMovieInFavoriteList(
            favoritesStorage
                .checkMovieInFavoriteList(
                    movieDetails.id.toString()
                )
        )

    }

    fun deleteFromFavorite(movieDetails: String?) {
        movieDetails?.let { favoritesStorage.deleteFromFavoriteList(it) }
        viewState.actionMovieIsNotInFavoriteList()
    }

    fun addToFavorite(movieDetails: MovieDetailsLocal) {
        movieDetails.let { favoritesStorage.saveMovieToFavorite(it) }
        viewState.actionMovieIsInFavoriteList()
    }

}