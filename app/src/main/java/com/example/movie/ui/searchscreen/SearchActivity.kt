package com.example.movie.ui.searchscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.movie.R
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

        findViewById<ImageButton>(R.id.imgBtnSearch)
            .setOnClickListener {
                searchPresenter.searchMovie(getSearchValue())
            }

        findViewById<Button>(R.id.btnSearchFavorite)
            .setOnClickListener {
                searchPresenter.openFavorite()
            }
    }

    private fun getSearchValue(): String {

        return searchValueField?.text.toString()
    }

    override fun openSearchResultActivity(searchValue: String) {
        val intent = Intent(this, SearchResultActivity::class.java)

        intent.putExtra(SearchResultActivity.TEXT_FOR_SEARCH, searchValue)

        startActivity(intent)
    }

    override fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)

        startActivity(intent)
    }

}