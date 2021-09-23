package com.example.movie.ui.moviescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movie.R

class MovieActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_DETAILS = "movie_details"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}