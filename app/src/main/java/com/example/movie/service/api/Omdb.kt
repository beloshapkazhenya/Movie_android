package com.example.movie.service.api

import com.example.movie.service.OmdbServices

object Omdb {

    private const val BASE_URL = "http://www.omdbapi.com/"

    val omdbServices: OmdbServices?
        get() = OmdbClient.getClient(BASE_URL)
            ?.create(OmdbServices::class.java)
}