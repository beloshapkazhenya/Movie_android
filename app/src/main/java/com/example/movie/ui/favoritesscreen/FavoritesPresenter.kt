package com.example.movie.ui.favoritesscreen

import com.example.movie.App
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.repository.FavoritesStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.instance

@InjectViewState
class FavoritesPresenter : MvpPresenter<FavoritesView>() {

    private val favoritesStorage: FavoritesStorage by App.kodein.instance()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun openMovieDetails(movieDetails: MovieDetailsLocal) {
        viewState.openMovieActivity(movieDetails)
    }

    private fun updateFavoritesActivity(favoriteList: MutableList<MovieDetailsLocal>) {
        viewState.updateFavoritesList(favoriteList)
    }

    private fun getFavoriteList(): MutableList<MovieDetailsLocal> {

        return favoritesStorage.getFavoritesList()
    }

    fun onCreate(itemClickObservable: Observable<MovieDetailsLocal>?) {
        updateFavoritesActivity(getFavoriteList())

        itemClickObservable
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe {
                openMovieDetails(it)
            }?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}