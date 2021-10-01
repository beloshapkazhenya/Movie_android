package com.example.movie.ui.favoritesscreen

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.ui.favoritesscreen.favoritesadapter.FavoritesAdapter
import com.example.movie.ui.moviescreen.MovieActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {

    @InjectPresenter
    lateinit var favoritesPresenter: FavoritesPresenter

    private var favoritesAdapter: FavoritesAdapter? = null
    private var recyclerResultView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        favoritesPresenter.initFavoritesStorage()

        recyclerResultView = findViewById(R.id.vRvFavoritesList)

    }

    override fun onResume() {
        super.onResume()
        favoritesPresenter.updateFavoritesActivity()
    }

    override fun updateFavorites(favoriteList: ArrayList<MovieDetailsLocal>) {

        favoritesAdapter = FavoritesAdapter(this, favoriteList) { id ->
            onMovieCardClick(id)
        }

        favoritesAdapter?.notifyDataSetChanged()

        recyclerResultView?.adapter = favoritesAdapter
    }

    override fun onMovieCardClick(id: String) {
        favoritesPresenter.openMovieDetails(id)
    }

    override fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        val intent = Intent(this, MovieActivity::class.java)

        intent.putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)

        startActivity(intent)
    }
}