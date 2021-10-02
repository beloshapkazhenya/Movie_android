package com.example.movie.model.response.moviesearch

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Response") val response: String,
    @SerializedName("Search") val search: List<SearchItemResponse>,
    @SerializedName("totalResults") val totalResults: String
)

