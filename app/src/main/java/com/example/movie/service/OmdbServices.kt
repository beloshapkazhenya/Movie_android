package com.example.movie.service

import com.example.movie.model.response.moviedetails.MovieDetailsResponse
import com.example.movie.model.response.moviesearch.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbServices {
    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("s") searchValue: String,
        @Query("page") page: Int
    ): Observable<SearchResponse>

    @GET(".")
    fun getMovieById(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String,
        @Query("plot") plotType: String
    ): Observable<MovieDetailsResponse>
}