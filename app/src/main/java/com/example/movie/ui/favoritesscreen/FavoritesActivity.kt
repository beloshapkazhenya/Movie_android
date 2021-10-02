package com.example.movie.ui.favoritesscreen

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.ui.favoritesscreen.favoritesadapter.FavoritesAdapter
import com.example.movie.ui.moviescreen.MovieActivity
import com.example.movie.ui.searchresultscreen.SearchResultActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {

    @InjectPresenter
    lateinit var favoritesPresenter: FavoritesPresenter

    private var favoritesAdapter: FavoritesAdapter? = null
    private var recyclerResultView: RecyclerView? = null

    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recyclerResultView = findViewById(R.id.vRvFavoritesList)

    }

    override fun onResume() {
        super.onResume()
        favoritesPresenter.updateFavoritesActivity()
    }

    override fun updateFavorites(favoriteList: MutableList<MovieDetailsLocal>) {

        favoritesAdapter = FavoritesAdapter(this, favoriteList as ArrayList<MovieDetailsLocal>) { id ->
            if (fastClickCheck()) {
                return@FavoritesAdapter
            }
            lastClickTime = SystemClock.elapsedRealtime()
            onMovieCardClick(id)
        }

        favoritesAdapter?.notifyDataSetChanged()

        recyclerResultView?.adapter = favoritesAdapter
    }

    private fun fastClickCheck(): Boolean {
        return (SystemClock.elapsedRealtime() - lastClickTime < SearchResultActivity.MIN_CLICK_DELAY)
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