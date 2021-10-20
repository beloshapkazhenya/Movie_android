package com.example.movie.repository

import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.moviesearch.SearchItemResponse
import com.example.movie.service.api.Omdb.omdbServices
import io.reactivex.Observable

class MovieRepository {
    companion object {
        private const val API_KEY: String = "ea6e1810"
        const val PLOT_TYPE: String = "full"
    }

    fun getMovieListByTitle(searchValue: String, page: Int): Observable<List<SearchItemResponse>>? {

        return omdbServices
            ?.getMoviesByTitle(API_KEY, searchValue, page)
            ?.map { searchResponse ->
                searchResponse.search?.map { search ->
                    SearchItemResponse(
                        search.poster,
                        search.title,
                        search.type,
                        search.year,
                        search.imdbID
                    )
                }
            }
    }

    fun getMovieById(imdbID: String): Observable<MovieDetailsLocal> {

        return omdbServices
            ?.getMovieById(API_KEY, imdbID, PLOT_TYPE)
            ?.map { movie ->
                MovieDetailsLocal(
                    movie.actors,
                    movie.country,
                    movie.director,
                    movie.genre,
                    movie.plot,
                    movie.poster,
                    movie.released,
                    movie.runtime,
                    movie.title,
                    movie.writer,
                    movie.imdbID
                )
            } ?: Observable.fromCallable { MovieDetailsLocal() }
    }
}