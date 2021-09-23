package com.example.movie.service

import com.example.movie.model.response.searchbyid.MovieDetails
import com.example.movie.model.response.searchbytitle.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DatabaseServices {
    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("s") searchValue: String
    ): Call<SearchResponse>

    @GET(".")
    fun getMovieById(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ):Call<MovieDetails>
}