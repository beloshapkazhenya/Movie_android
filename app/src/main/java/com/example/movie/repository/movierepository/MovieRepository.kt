package com.example.movie.repository.movierepository

import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.repository.movierepository.Database.databaseServices
import io.reactivex.Observable

class MovieRepository {

    companion object {
        private const val API_KEY: String = "ea6e1810"
        const val PLOT_TYPE: String = "full"
    }

    fun getMovieListByTitle(searchValue: String): Observable<List<Search>>? {

        return databaseServices
            ?.getMoviesByTitle(API_KEY, searchValue)
            ?.map { searchResponse ->
                searchResponse.Search.map { search ->
                    Search(
                        search.Poster,
                        search.Title,
                        search.Type,
                        search.Year,
                        search.imdbID
                    )
                }
            }
    }

    fun getMovieById(imdbID: String): Observable<MovieDetailsLocal> {

        return databaseServices
            ?.getMovieById(API_KEY, imdbID, PLOT_TYPE)
            ?.map { movie ->
                MovieDetailsLocal(
                    movie.Actors,
                    movie.Country,
                    movie.Director,
                    movie.Genre,
                    movie.Plot,
                    movie.Poster,
                    movie.Released,
                    movie.Runtime,
                    movie.Title,
                    movie.Writer,
                    movie.imdbID
                )
            } ?: Observable.fromCallable{MovieDetailsLocal()}

    }

}