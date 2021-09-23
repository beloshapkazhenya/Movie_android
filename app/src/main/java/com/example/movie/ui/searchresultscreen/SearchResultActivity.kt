package com.example.movie.ui.searchresultscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.response.Search
import java.util.ArrayList

class SearchResultActivity : AppCompatActivity() {
    companion object {
        const val SEARCH_RESULT = "search_result"
        const val SEARCH_TITLE = "search_title"
    }

    var searchTitle: TextView? = null
    var searchResultAdapter: SearchResultAdapter? = null
    var recyclerResultView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
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
        searchResultAdapter = getSearchResult()?.let { SearchResultAdapter(this, it) }
        searchResultAdapter?.notifyDataSetChanged()
        recyclerResultView?.adapter = searchResultAdapter
    }
}