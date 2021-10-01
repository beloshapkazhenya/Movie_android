package com.example.movie.ui.searchscreen

import com.example.movie.App
import com.example.movie.model.response.searchbytitle.Search
import com.example.movie.repository.movierepository.MovieRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.instance


@InjectViewState
class SearchPresenter : MvpPresenter<SearchView>() {


    private val movieRepository: MovieRepository by App.kodein.instance()

    private fun searchMovieOnRepository(searchValue: String) {
        movieRepository
            .getMovieListByTitle(searchValue)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<Search>> {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                    viewState.hideLoader()
                }

                override fun onNext(response: List<Search>) {
                    openSearchResult(response, searchValue)
                }
            })
    }

    fun openSearchResult(response: List<Search>, searchValue: String) {
        viewState
            .openSearchResultActivity(
                response as ArrayList<Search>,
                searchValue
            )
    }

    fun searchMovie(searchValue: String) {
        viewState.showLoader()

        searchMovieOnRepository(searchValue)
    }

}