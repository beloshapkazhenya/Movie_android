package com.example.movie.ui.searchscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.repository.Database
import com.example.movie.repository.movierepository.MovieRepository
import com.example.movie.service.DatabaseServices
import com.example.movie.ui.favoritesscreen.FavoritesActivity
import com.example.movie.ui.searchresultscreen.SearchResultActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchActivity : AppCompatActivity() {


    private var searchValueField: EditText? = null
    private var databaseServices: DatabaseServices? = null
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchValueField = findViewById(R.id.edTxtSearchField)
        databaseServices = Database.databaseServices

        movieRepository = MovieRepository()

        findViewById<ImageButton>(R.id.imgBtnSearch).setOnClickListener {
            searchMovie(getSearchValue())
        }

        findViewById<Button>(R.id.btnSearchFavorite).setOnClickListener {
            openFavorite()
        }
    }

    private fun getSearchValue(): String {
        return searchValueField?.text.toString()
    }

    private fun searchMovie(searchValue: String) {
        movieRepository.getMovieListByTitle(searchValue)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Search>> {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

                override fun onNext(response: List<Search>) {
                    openSearchResultActivity(response as ArrayList<Search>, searchValue)
                }
            })
    }


    private fun openSearchResultActivity(searchList: ArrayList<Search>, searchValue: String) {
        val intent = Intent(this, SearchResultActivity::class.java)

        intent.putExtra(SearchResultActivity.SEARCH_RESULT, searchList)
        intent.putExtra(SearchResultActivity.SEARCH_TITLE, searchValue)

        startActivity(intent)
    }

    private fun openFavorite() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
}