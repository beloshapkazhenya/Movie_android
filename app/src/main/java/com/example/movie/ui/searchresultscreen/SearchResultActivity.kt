package com.example.movie.ui.searchresultscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.repository.movierepository.MovieRepository
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.moviescreen.MovieActivity
import com.example.movie.ui.searchresultscreen.searchresultadapter.SearchResultAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class SearchResultActivity : AppCompatActivity() {
    companion object {
        const val SEARCH_RESULT = "search_result"
        const val SEARCH_TITLE = "search_title"
    }

    var searchTitle: TextView? = null
    var searchResultAdapter: SearchResultAdapter? = null
    var recyclerResultView: RecyclerView? = null
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        movieRepository = MovieRepository()

        findAllView()

        findViewById<Button>(R.id.btnSearchResultFavorite).setOnClickListener {
            openFavorite()
        }

        updateSearchTitle()
        updateSearchResult()
    }

    private fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
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
        movieRepository.getMovieById(imdbID)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieDetailsLocal> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MovieDetailsLocal) {
                    openMovieActivity(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }

            })
    }


    fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)

        startActivity(intent)
    }
}