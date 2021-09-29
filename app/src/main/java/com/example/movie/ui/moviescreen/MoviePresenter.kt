package com.example.movie.ui.moviescreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MoviePresenter : MvpPresenter<MovieView>() {

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

    fun checkMovieInFavoriteList(isInFavorite: Boolean) {

        when (isInFavorite) {
            true -> viewState.actionMovieIsNotInFavoriteList()
            false -> viewState.actionMovieIsInFavoriteList()
        }

    }

}