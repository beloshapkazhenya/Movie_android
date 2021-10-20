package com.example.movie.ui.searchscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.movie.R
import com.example.movie.base.safeonclicklistener.setSafeOnClickListener
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.searchresultscreen.SearchResultActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class SearchActivity : MvpAppCompatActivity(), SearchView {

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    private var searchValueField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchValueField = findViewById(R.id.edTxtSearchField)

        addSearchButtonOnClickListener()

        addFavoritesButtonOnClickListener()
    }

    private fun addSearchButtonOnClickListener() {
        findViewById<ImageButton>(R.id.imgBtnSearch)
            .setSafeOnClickListener {
                searchPresenter.searchMovie(getSearchValue())
            }
    }

    private fun addFavoritesButtonOnClickListener() {
        findViewById<Button>(R.id.btnSearchFavorite)
            .setSafeOnClickListener {
                searchPresenter.openFavorite()
            }
    }

    private fun getSearchValue(): String {

        return searchValueField?.text.toString()
    }

    override fun openSearchResultActivity(searchValue: String) {
        startActivity(
            Intent(this, SearchResultActivity::class.java).apply {
                putExtra(SearchResultActivity.TEXT_FOR_SEARCH, searchValue)
            }
        )
    }

    override fun openFavorite() {
        startActivity(
            Intent(this, FavoritesActivity::class.java)
        )
    }
}