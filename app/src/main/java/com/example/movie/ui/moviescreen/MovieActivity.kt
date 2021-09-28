package com.example.movie.ui.moviescreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MovieActivity : MvpAppCompatActivity(), MovieView {

    companion object {
        const val MOVIE_DETAILS = "movie_details"
    }

    @InjectPresenter
    lateinit var moviePresenter: MoviePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        getMovieDetails()?.let { moviePresenter.updateMovieDetails(it) }

        findViewById<Button>(R.id.btnMovieFavorite)
            .setOnClickListener {
                openFavorite()
            }
    }

    private fun getMovieDetails(): MovieDetailsLocal? {
        return intent.getParcelableExtra(MOVIE_DETAILS)
    }

    override fun updatePlot(plot: String) {
        findViewById<TextView>(R.id.vTvMoviePlot)
            .text = plot
    }

    override fun updateActors(actors: String) {
        findViewById<TextView>(R.id.vTvMovieActors)
            .text = actors
    }

    override fun updateCountry(country: String) {
        findViewById<TextView>(R.id.vTvMovieCountry)
            .text = country
    }

    override fun updateWriter(writer: String) {
        findViewById<TextView>(R.id.vTvMovieWriter)
            .text = writer
    }

    override fun updateDirector(director: String) {
        findViewById<TextView>(R.id.vTvMovieDirector)
            .text = director
    }

    override fun updateGenre(genre: String) {
        findViewById<TextView>(R.id.vTvMovieGenre)
            .text = genre
    }

    override fun updateRuntime(runtime: String) {
        findViewById<TextView>(R.id.vTvMovieRuntime)
            .text = runtime
    }

    override fun updateReleased(released: String) {
        findViewById<TextView>(R.id.vTvMovieReleased)
            .text = released
    }

    override fun updateTitle(title: String) {
        findViewById<TextView>(R.id.vTvMovieTitle)
            .text = title
    }

    override fun updateMoviePoster(poster: String) {
        Glide
            .with(this)
            .load(poster)
            .into(findViewById(R.id.vImgVwMoviePoster))
    }

    override fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)

        startActivity(intent)
    }
}