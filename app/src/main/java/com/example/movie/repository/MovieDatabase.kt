package com.example.movie.repository

import android.app.Application

class MovieDatabase: Application() {
    private lateinit var movieDatabase: MovieDatabase
    override fun onCreate() {
        super.onCreate()

    }
}