package com.example.movie.ui.searchscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import com.example.movie.R
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.searchresultscreen.SearchResultActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class SearchActivity : MvpAppCompatActivity(), SearchView {

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    private var searchValueField: EditText? = null
    private var progressBar: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        findAllView()

        findViewById<ImageButton>(R.id.imgBtnSearch)
            .setOnClickListener {
                searchPresenter.searchMovie(getSearchValue())
            }

        findViewById<Button>(R.id.btnSearchFavorite)
            .setOnClickListener {
                openFavorite()
            }
    }

    private fun findAllView() {
        progressBar = findViewById(R.id.vFrLtProgressBar)
        searchValueField = findViewById(R.id.edTxtSearchField)
    }

    private fun getSearchValue(): String {

        return searchValueField?.text.toString()
    }

    override fun showLoader() {
        progressBar
            ?.visibility = FrameLayout.VISIBLE
    }

    override fun hideLoader() {
        progressBar
            ?.visibility = FrameLayout.INVISIBLE
    }

    override fun openSearchResultActivity(
        searchList: ArrayList<Search>,
        searchValue: String
    ) {
        val intent = Intent(this, SearchResultActivity::class.java)

        intent.putExtra(SearchResultActivity.SEARCH_RESULT, searchList)
        intent.putExtra(SearchResultActivity.SEARCH_TITLE, searchValue)

        startActivity(intent)
    }

    override fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)

        startActivity(intent)
    }
}