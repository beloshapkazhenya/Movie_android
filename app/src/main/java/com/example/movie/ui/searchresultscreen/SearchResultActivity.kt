package com.example.movie.ui.searchresultscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.moviesearch.SearchItemResponse
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.moviescreen.MovieActivity
import com.example.movie.ui.searchresultscreen.searchresultadapter.SearchResultAdapter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class SearchResultActivity : MvpAppCompatActivity(), SearchResultView {

    companion object {
        const val TEXT_FOR_SEARCH = "TEXT_FOR_SEARCH"
    }

    @InjectPresenter
    lateinit var searchResultPresenter: SearchResultPresenter

    private var progressBar: FrameLayout? = null
    private var recyclerResultView: RecyclerView? = null
    private var searchTitleView: TextView? = null

    private var isLoading: Boolean = false
    private lateinit var searchTitle: String

    private var recyclerViewLayoutManager: RecyclerView.LayoutManager? = null
    private var searchResultAdapter: SearchResultAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        findAllView()
        setSearchTitle()
        addFavoriteButtonOnClickListener()

        initSearchResultRv()

        searchResultPresenter.onCreate(
            getTextForSearch(),
            searchResultAdapter?.itemClickObservable
        )

    }

    override fun updateSearchTitle(title: String) {
        searchTitleView?.text = title
    }

    private fun addFavoriteButtonOnClickListener(){
        findViewById<Button>(R.id.btnSearchResultFavorite)
            .setOnClickListener {
                openFavorite()
            }
    }

    private fun getTextForSearch(): String? {

        return intent.getStringExtra(TEXT_FOR_SEARCH)
    }

    private fun initSearchResultRv() {
        searchResultAdapter = SearchResultAdapter(mutableListOf())
        recyclerResultView?.adapter = searchResultAdapter

        recyclerViewLayoutManager = recyclerResultView?.layoutManager

        addOnScrollListener()
    }

    override fun openFavorite() {
        startActivity(
            Intent(this, FavoritesActivity::class.java)
        )
    }

    private fun findAllView() {
        searchTitleView = findViewById(R.id.vTvSearchResultTitle)
        recyclerResultView = findViewById(R.id.vRvSearchResultList)
        progressBar = findViewById(R.id.vFrLtProgressBar)
    }

    override fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        startActivity(
            Intent(this, MovieActivity::class.java).apply {
                putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)
            }
        )
    }

    override fun updateSearchResultList(items: List<SearchItemResponse>) {
        searchResultAdapter?.setItems(items)

        isLoading = false
    }

    override fun addNextSearchResultPage(items: List<SearchItemResponse>) {
        items.map { searchResultAdapter?.addItem(it) }

        isLoading = false
    }

    override fun showLoader() {
        progressBar
            ?.visibility = FrameLayout.VISIBLE
    }

    override fun hideLoader() {
        progressBar
            ?.visibility = FrameLayout.INVISIBLE
    }

    override fun showMessage() {
        Toast
            .makeText(
                this,
                getString(R.string.text_error),
                Toast.LENGTH_LONG
            )
            .show()
    }

    private fun setSearchTitle() {
        searchTitle = getTextForSearch().toString()
    }


    private fun addOnScrollListener() {

        recyclerResultView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {

                    if (
                        (recyclerViewLayoutManager as? LinearLayoutManager)
                            ?.findLastCompletelyVisibleItemPosition() ==
                        searchResultAdapter?.itemCount?.minus(1) && searchResultAdapter?.itemCount?.minus(
                            1
                        ) != null
                    ) {
                        searchResultPresenter.searchNextPage(searchTitle)

                        isLoading = true
                    }

                }

            }
        })

    }


}