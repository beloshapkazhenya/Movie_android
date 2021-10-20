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

        recyclerResultView = findViewById(R.id.vRvFavoritesList)

        initFavoritesRv()

        favoritesPresenter.onCreate(
            favoritesAdapter?.itemClickObservable
        )
    }

    private fun initFavoritesRv() {
        favoritesAdapter = FavoritesAdapter(mutableListOf())
        recyclerResultView?.adapter = favoritesAdapter
    }

    override fun updateFavoritesList(items: List<MovieDetailsLocal>) {
        favoritesAdapter?.setItems(items)
    }

    override fun openMovieActivity(movieDetailsLocal: MovieDetailsLocal) {
        startActivity(
            Intent(this, MovieActivity::class.java).apply {
                putExtra(MovieActivity.MOVIE_DETAILS, movieDetailsLocal)
            }
        )
    }
}