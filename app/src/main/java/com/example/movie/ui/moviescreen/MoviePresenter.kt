package com.example.movie.ui.moviescreen

import com.example.movie.model.local.MovieDetailsLocal
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MoviePresenter : MvpPresenter<MovieView>() {

    fun updateMovieDetails(movieDetails: MovieDetailsLocal) {
        viewState.updateTitle(movieDetails.title.toString())
        viewState.updateMoviePoster(movieDetails.poster.toString())
        viewState.updateReleased(movieDetails.released.toString())
        viewState.updateRuntime(movieDetails.runtime.toString())
        viewState.updateGenre(movieDetails.genre.toString())
        viewState.updateDirector(movieDetails.director.toString())
        viewState.updateWriter(movieDetails.writer.toString())
        viewState.updateCountry(movieDetails.country.toString())
        viewState.updateActors(movieDetails.actors.toString())
        viewState.updatePlot(movieDetails.plot.toString())
    }

}