package com.example.movie.ui.favoritesscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.repository.FavoritesStorage
import com.example.movie.ui.favoritesscreen.favoritesadapter.FavoritesAdapter
import com.example.movie.ui.moviescreen.MovieActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {

    @InjectPresenter
    lateinit var favoritesPresenter: FavoritesPresenter

    private var favoritesStorage: FavoritesStorage? = null


    private var favoritesAdapter: FavoritesAdapter? = null
    private var recyclerResultView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerResultView = findViewById(R.id.vRvFavoritesList)

        favoritesStorage = FavoritesStorage(this)
        favoritesStorage!!.setRealmConfiguration()
    }

    override fun onResume() {
        super.onResume()
        updateFavorites()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFavorites() {

        favoritesAdapter = favoritesStorage
            ?.getFavoritesList()
            ?.let {
                FavoritesAdapter(this, it) { id ->
                    onMovieCardClick(id)
                }
            }

        favoritesAdapter?.notifyDataSetChanged()

        recyclerResultView?.adapter = favoritesAdapter
    }

    override fun onMovieCardClick(id: String) {
        favoritesPresenter.openMovieDetails(id)
    }

    private fun getMovieDetails(id: String): MovieDetailsLocal? {

        return favoritesStorage?.getMovie(id)
    }

    override fun openMovieActivity(id: String) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, getMovieDetails(id))

        startActivity(intent)
    }
}