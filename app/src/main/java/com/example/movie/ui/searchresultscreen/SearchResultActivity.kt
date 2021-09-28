package com.example.movie.ui.searchresultscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.moviescreen.MovieActivity
import com.example.movie.ui.searchresultscreen.searchresultadapter.SearchResultAdapter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import java.util.*

class SearchResultActivity : MvpAppCompatActivity(), SearchResultView {

    companion object {
        const val SEARCH_RESULT = "search_result"
        const val SEARCH_TITLE = "search_title"
    }

    @InjectPresenter
    lateinit var searchResultPresenter: SearchResultPresenter

    private var searchTitle: TextView? = null
    private var searchResultAdapter: SearchResultAdapter? = null
    private var recyclerResultView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        findAllView()

        searchResultPresenter.updateSearchResult()

        findViewById<Button>(R.id.btnSearchResultFavorite).setOnClickListener {
            openFavorite()
        }

    }

    override fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)

        startActivity(intent)
    }

    private fun findAllView() {
        searchTitle = findViewById(R.id.vTvSearchResultTitle)
        recyclerResultView = findViewById(R.id.vRvSearchResultList)
    }

    private fun getSearchTitle(): String? {

        return intent.getStringExtra(SEARCH_TITLE)

    }

    override fun updateSearchTitle() {
        searchTitle?.text = getSearchTitle()
    }

    private fun getSearchResult(): ArrayList<Search>? {

        return intent.getParcelableArrayListExtra(SEARCH_RESULT)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateSearchResult() {

        searchResultAdapter = getSearchResult()?.let {
            SearchResultAdapter(this, it) { imdbID ->
                onMovieCardClick(imdbID)
            }
        }

        searchResultAdapter?.notifyDataSetChanged()
        recyclerResultView?.adapter = searchResultAdapter
    }

    override fun onMovieCardClick(imdbID: String) {
        searchResultPresenter.getMovieDetails(imdbID)
    }

    override fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)

        startActivity(intent)
    }
}