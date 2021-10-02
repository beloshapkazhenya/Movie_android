package com.example.movie.ui.searchresultscreen

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
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
        const val MIN_CLICK_DELAY = 500
    }

    @InjectPresenter
    lateinit var searchResultPresenter: SearchResultPresenter

    private var progressBar: FrameLayout? = null

    private var lastClickTime: Long = 0
    private var isLoading: Boolean = false
    private var recyclerViewLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var searchTitle: String

    private var searchTitleView: TextView? = null
    private var searchResultAdapter: SearchResultAdapter? = null
    private var recyclerResultView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        findAllView()
        setSearchTitle()

        findViewById<Button>(R.id.btnSearchResultFavorite)
            .setOnClickListener {
                openFavorite()
            }

        initSearchResultRv()

        searchResultPresenter.onCreate(
            getTextForSearch(),
            searchResultAdapter?.itemClickObservable
        )

    }

    override fun updateSearchTitle(title: String) {
        searchTitleView?.text = title
    }

    private fun getTextForSearch(): String? {

        return intent.getStringExtra(TEXT_FOR_SEARCH)
    }

    private fun initSearchResultRv() {
        searchResultAdapter = SearchResultAdapter(mutableListOf())
        recyclerResultView?.adapter = searchResultAdapter

        recyclerViewLayoutManager = recyclerResultView?.layoutManager

    }

    override fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)

        startActivity(intent)
    }

    private fun findAllView() {
        searchTitleView = findViewById(R.id.vTvSearchResultTitle)
        recyclerResultView = findViewById(R.id.vRvSearchResultList)
        progressBar = findViewById(R.id.vFrLtProgressBar)
    }

    override fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)

        startActivity(intent)
    }

    override fun updateSearchResultList(items: List<SearchItemResponse>) {
        searchResultAdapter?.setItems(items)

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

    private fun setSearchTitle() {
        searchTitle = getTextForSearch().toString()
    }


    private fun fastClickCheck(): Boolean {
        return (SystemClock.elapsedRealtime() - lastClickTime < MIN_CLICK_DELAY)
    }


//    @SuppressLint("NotifyDataSetChanged")
//    override fun updateSearchResult() {
//
//        searchResultAdapter = searchResultList?.let {
//            SearchResultAdapter(it) { imdbID ->
//                if (fastClickCheck()) {
//                    return@SearchResultAdapter
//                }
//                lastClickTime = SystemClock.elapsedRealtime()
//                onMovieCardClicked(imdbID)
//            }
//        }
//
//        searchResultAdapter?.registerAdapterDataObserver(object :
//            RecyclerView.AdapterDataObserver() {
//            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//                (recyclerViewLayoutManager as LinearLayoutManager).scrollToPositionWithOffset(
//                    positionStart,
//                    0
//                )
//            }
//        })
//
//        searchResultAdapter?.notifyDataSetChanged()
//
//        recyclerResultView?.adapter = searchResultAdapter
//
//        recyclerViewLayoutManager = recyclerResultView?.layoutManager
//
////        recyclerResultView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView, dx, dy)
////
////                if (!isLoading) {
////
////                    if (
////                        (recyclerViewLayoutManager as LinearLayoutManager)
////                            .findLastCompletelyVisibleItemPosition() ==
////                        (searchResultList?.size)?.minus(1)
////                    ) {
////                        searchResultPresenter.getNextSearchPage(searchTitle, nextSearchPage)
////
////                        nextSearchPage++
////
////                        isLoading = true
////                    }
////
////                }
////
////            }
////        })
//
//    }


}