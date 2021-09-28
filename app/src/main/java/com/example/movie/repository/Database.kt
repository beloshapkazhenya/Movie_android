package com.example.movie.repository

import com.example.movie.service.DatabaseServices

object Database {
    private const val BASE_URL = "http://www.omdbapi.com/"
    val databaseServices: DatabaseServices?

        get() = DatabaseClient
            .getClient(BASE_URL)
            ?.create(DatabaseServices::class.java)
}