package com.example.movie.ui.searchresultscreen

import com.example.movie.App
import com.example.movie.model.local.MovieDetailsLocal
import com.example.movie.model.response.moviesearch.SearchItemResponse
import com.example.movie.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.instance

@InjectViewState
class SearchResultPresenter : MvpPresenter<SearchResultView>() {

    private val movieRepository: MovieRepository by App.kodein.instance()

    private var searchPage: Int = 1

    private fun getMovieDetails(imdbID: String) {
        movieRepository.getMovieById(imdbID)
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<MovieDetailsLocal> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(movieDetailsLocal: MovieDetailsLocal) {
                    showMovieDetails(movieDetailsLocal)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                    viewState.showMessage()
                }

                override fun onComplete() {
                }

            })
    }

    private fun searchMovie(searchValue: String, page: Int) {
        movieRepository.getMovieListByTitle(searchValue, page)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<SearchItemResponse>> {
                override fun onSubscribe(d: Disposable) {
                    viewState.showLoader()
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                    viewState.hideLoader()
                    viewState.showMessage()
                }

                override fun onComplete() {
                    viewState.hideLoader()
                }

                override fun onNext(list: List<SearchItemResponse>) {
                    viewState.updateSearchResultList(list)
                }

            })
    }

    fun showMovieDetails(movieDetailsLocal: MovieDetailsLocal) {
        viewState.openMovieActivity(movieDetailsLocal)
    }

    fun onCreate(textForSearch: String?, itemClickObservable: Observable<SearchItemResponse>?) {
        textForSearch?.let { searchMovie(it, searchPage) }

        viewState.updateSearchTitle(textForSearch.toString())

        itemClickObservable
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe {
                getMovieDetails(it.imdbID.toString())
            }

    }


//    fun searchMovie(searchValue: String) {
//        viewState.showLoader()
//
//        searchMovieOnRepository(searchValue)
//    }
    //    companion object{
//        private const val START_SEARCH_PAGE: Int = 1
//    }
//
//    private val movieRepository: MovieRepository by App.kodein.instance()

//    private fun searchMovieOnRepository(searchValue: String) {
//        movieRepository
//            .getMovieListByTitle(searchValue, START_SEARCH_PAGE)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe(object : Observer<List<SearchItemResponse>> {
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onComplete() {
//                    viewState.hideLoader()
//                }
//
//                override fun onNext(response: List<SearchItemResponse>) {
//                    openSearchResult(response, searchValue)
//                }
//            })
//    }

}