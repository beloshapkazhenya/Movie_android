package com.example.movie.service

import com.example.movie.model.response.searchbyid.MovieDetails
import com.example.movie.model.response.searchbytitle.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DatabaseServices {
    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("s") searchValue: String
    ): Observable<SearchResponse>

    @GET(".")
    fun getMovieById(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String,
        @Query("plot") plotType:String
    ):Observable<MovieDetails>
}