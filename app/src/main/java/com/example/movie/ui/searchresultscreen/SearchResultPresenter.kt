package com.example.movie.ui.searchresultscreen

import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.repository.movierepository.MovieRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SearchResultPresenter: MvpPresenter<SearchResultView>() {

    private var movieRepository = MovieRepository()

    fun getMovieDetails(imdbID: String) {
        movieRepository.getMovieById(imdbID)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieDetailsLocal> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(movieDetailsLocal: MovieDetailsLocal) {
                    showMovieDetails(movieDetailsLocal)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }

                override fun onComplete() {
                }

            })
    }

    fun updateSearchResult(){
        viewState.updateSearchTitle()
        viewState.updateSearchResult()
    }

    fun showMovieDetails(movieDetailsLocal: MovieDetailsLocal) {
        viewState.openMovieActivity(movieDetailsLocal)
    }
}