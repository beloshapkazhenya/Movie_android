package com.example.movie.ui.searchscreen

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.model.response.Search
import com.example.movie.model.response.SearchResponse
import com.example.movie.repository.Database
import com.example.movie.service.DatabaseServices
import com.example.movie.ui.searchresultscreen.SearchResultActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private var searchValueField: EditText? = null
    private var databaseServices: DatabaseServices? = null

    companion object {
        private const val API_KEY: String = "ea6e1810"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchValueField = findViewById(R.id.edTxtSearchField)
        databaseServices = Database.databaseServices

        findViewById<ImageButton>(R.id.imgBtnSearch).setOnClickListener {
            searchMovie()

        }

    }

    private fun getSearchValue(): String {
        return searchValueField?.text.toString()
    }

    private fun searchMovie() {
        getMovieList()
    }

    private fun getMovieList() {
        databaseServices
            ?.getMoviesByTitle(API_KEY, getSearchValue())
            ?.enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Toast.makeText(this@SearchActivity, "sasdasd", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.body()?.Response == "True") {
                        openSearchResultActivity(
                            response.body()!!.Search as ArrayList<Search>,
                            getSearchValue()
                        )
                    }
                }
            })
    }

    private fun openSearchResultActivity(searchList: ArrayList<Search>, searchValue: String) {
        val intent = Intent(this, SearchResultActivity::class.java)

        intent.putExtra(SearchResultActivity.SEARCH_RESULT, searchList)
        intent.putExtra(SearchResultActivity.SEARCH_TITLE, searchValue)

        startActivity(intent)
    }


}