package com.example.movie.ui.searchresultscreen

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.searchbyid.MovieDetails
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.repository.Database
import com.example.movie.service.DatabaseServices
import com.example.movie.ui.moviescreen.MovieActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchResultActivity : AppCompatActivity() {
    companion object {
        const val SEARCH_RESULT = "search_result"
        const val SEARCH_TITLE = "search_title"
        private const val API_KEY: String = "ea6e1810"
        const val PLOT_TYPE: String = "full"
    }

    var searchTitle: TextView? = null
    var searchResultAdapter: SearchResultAdapter? = null
    var recyclerResultView: RecyclerView? = null
    var databaseServices: DatabaseServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        databaseServices = Database.databaseServices

        findAllView()

        updateSearchTitle()
        updateSearchResult()
    }

    private fun findAllView() {
        searchTitle = findViewById(R.id.vTvSearchResultTitle)
        recyclerResultView = findViewById(R.id.vRvSearchResultList)
    }

    fun getSearchTitle(): String? {
        return intent.getStringExtra(SEARCH_TITLE)
    }

    fun updateSearchTitle() {
        searchTitle?.text = getSearchTitle()
    }

    fun getSearchResult(): ArrayList<Search>? {
        return intent.getParcelableArrayListExtra(SEARCH_RESULT)
    }

    fun updateSearchResult() {
        searchResultAdapter = getSearchResult()?.let {
            SearchResultAdapter(this, it) { imdbID ->
                onMovieCardClick(imdbID)
            }
        }
        searchResultAdapter?.notifyDataSetChanged()
        recyclerResultView?.adapter = searchResultAdapter
    }

    fun onMovieCardClick(imdbID: String) {
        getMovieDetails(imdbID)
    }

    fun getMovieDetails(imdbID: String) {
        databaseServices?.getMovieById(API_KEY, imdbID, PLOT_TYPE)?.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                val movieDetailsLocal = MovieDetailsLocal(
                    actors = response.body()?.Actors.toString(),
                    response.body()?.Country.toString(),
                    response.body()?.Director.toString(),
                    response.body()?.Genre.toString(),
                    response.body()?.Plot.toString(),
                    response.body()?.Poster.toString(),
                    response.body()?.Released.toString(),
                    response.body()?.Runtime.toString(),
                    response.body()?.Title.toString(),
                    response.body()?.Writer.toString(),
                    response.body()?.imdbID.toString()
                )
                openMovieActivity(movieDetailsLocal)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)

        startActivity(intent)
    }
}