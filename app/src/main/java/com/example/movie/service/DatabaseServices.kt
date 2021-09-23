package com.example.movie.service

import com.example.movie.model.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DatabaseServices {
    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("s") searchValue: String
    ): Call<SearchResponse>
}