package com.example.movie.ui.moviescreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.base.safeonclicklistener.setSafeOnClickListener
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MovieActivity : MvpAppCompatActivity(), MovieView {

    companion object {
        const val MOVIE_DETAILS = "MOVIE_DETAILS"
    }

    @InjectPresenter
    lateinit var moviePresenter: MoviePresenter

    private var movieDetails: MovieDetailsLocal? = null

    private var addToFavoriteButton: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        addToFavoriteButton = findViewById(R.id.btnAddToFavorite)

        getMovieDetails()

        movieDetails?.let {
            moviePresenter.updateMovieDetails(it)
            moviePresenter.checkMovieInFavoriteList(it)
        }

        addFavoritesOnClickListener()
    }

    private fun addFavoritesOnClickListener() {
        findViewById<Button>(R.id.btnMovieFavorite).setSafeOnClickListener {
            openFavorite()
        }
    }

    override fun actionMovieIsInFavoriteList() {

        addToFavoriteButton?.apply {
            text = getString(R.string.delete_from_favorite)

            setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.button_red
                )
            )

            setSafeOnClickListener {
                moviePresenter.deleteFromFavorite(movieDetails?.id)
            }
        }
    }

    override fun actionMovieIsNotInFavoriteList() {
        addToFavoriteButton?.apply {
            text = getString(R.string.add_to_favorite)

            setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.button
                )
            )

            setSafeOnClickListener {
                movieDetails?.let { movieDetailsLocal ->
                    moviePresenter.addToFavorite(
                        movieDetailsLocal
                    )
                }
            }
        }
    }

    private fun getMovieDetails() {
        movieDetails = intent.getParcelableExtra(MOVIE_DETAILS)
    }

    override fun updateUI(movieDetailsLocal: MovieDetailsLocal) {
        updateTitle(movieDetailsLocal.title.toString())
        updateMoviePoster(movieDetailsLocal.poster.toString())
        updateReleased(movieDetailsLocal.released.toString())
        updateRuntime(movieDetailsLocal.runtime.toString())
        updateGenre(movieDetailsLocal.genre.toString())
        updateDirector(movieDetailsLocal.director.toString())
        updateWriter(movieDetailsLocal.writer.toString())
        updateCountry(movieDetailsLocal.country.toString())
        updateActors(movieDetailsLocal.actors.toString())
        updatePlot(movieDetailsLocal.plot.toString())
    }

    private fun updatePlot(plot: String) {
        findViewById<TextView>(R.id.vTvMoviePlot)
            .text = plot
    }

    private fun updateActors(actors: String) {
        findViewById<TextView>(R.id.vTvMovieActors)
            .text = String.format(getString(R.string.actors), actors)
    }

    private fun updateCountry(country: String) {
        findViewById<TextView>(R.id.vTvMovieCountry)
            .text = String.format(getString(R.string.country), country)
    }

    private fun updateWriter(writer: String) {
        findViewById<TextView>(R.id.vTvMovieWriter)
            .text = String.format(getString(R.string.writer), writer)
    }

    private fun updateDirector(director: String) {
        findViewById<TextView>(R.id.vTvMovieDirector)
            .text = String.format(getString(R.string.director), director)
    }

    private fun updateGenre(genre: String) {
        findViewById<TextView>(R.id.vTvMovieGenre)
            .text = String.format(getString(R.string.genre), genre)
    }

    private fun updateRuntime(runtime: String) {
        findViewById<TextView>(R.id.vTvMovieRuntime)
            .text = String.format(getString(R.string.runtime), runtime)
    }

    private fun updateReleased(released: String) {
        findViewById<TextView>(R.id.vTvMovieReleased)
            .text = String.format(getString(R.string.released), released)
    }

    private fun updateTitle(title: String) {
        findViewById<TextView>(R.id.vTvMovieTitle)
            .text = title
    }

    private fun updateMoviePoster(poster: String) {
        Glide
            .with(this)
            .load(poster)
            .placeholder(R.drawable.movie_poster_placeholder)
            .error(R.drawable.movie_poster_placeholder)
            .into(findViewById(R.id.vImgVwMoviePoster))
    }

    override fun openFavorite() {
        startActivity(
            Intent(this, FavoritesActivity::class.java)
        )
    }
}