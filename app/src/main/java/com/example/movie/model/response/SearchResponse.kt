package com.example.movie.model.response

data class SearchResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

