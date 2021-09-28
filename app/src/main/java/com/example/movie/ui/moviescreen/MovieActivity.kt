package com.example.movie.ui.moviescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.ui.favoritesscreen.FavoritesActivity

class MovieActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_DETAILS = "movie_details"
    }
    private var movieDetails: MovieDetailsLocal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieDetails = getMovieDetails()
        movieDetails?.let { updateMovieDetails(it) }

        findViewById<Button>(R.id.btnMovieFavorite).setOnClickListener {
            openFavorite()
        }
    }

    private fun getMovieDetails(): MovieDetailsLocal? {
        return intent.getParcelableExtra(MOVIE_DETAILS)
    }

    fun updateMovieDetails(movieDetails:MovieDetailsLocal){
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

    private fun updatePlot(plot: String) {
        findViewById<TextView>(R.id.vTvMoviePlot).text = plot
    }

    private fun updateActors(actors: String) {
        findViewById<TextView>(R.id.vTvMovieActors).text = actors
    }

    private fun updateCountry(country: String) {
        findViewById<TextView>(R.id.vTvMovieCountry).text = country
    }

    private fun updateWriter(writer: String) {
        findViewById<TextView>(R.id.vTvMovieWriter).text = writer
    }

    private fun updateDirector(director: String) {
        findViewById<TextView>(R.id.vTvMovieDirector).text = director
    }

    private fun updateGenre(genre: String) {
        findViewById<TextView>(R.id.vTvMovieGenre).text = genre
    }

    private fun updateRuntime(runtime: String) {
        findViewById<TextView>(R.id.vTvMovieRuntime).text = runtime
    }

    private fun updateReleased(released:String) {
        findViewById<TextView>(R.id.vTvMovieReleased).text = released
    }

    private fun updateMoviePoster(poster:String) {
        Glide.with(this).load(poster).into(findViewById(R.id.vImgVwMoviePoster))
    }

    private fun updateTitle(title:String) {
        findViewById<TextView>(R.id.vTvMovieTitle).text = title
    }

    private fun openFavorite(){
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
}