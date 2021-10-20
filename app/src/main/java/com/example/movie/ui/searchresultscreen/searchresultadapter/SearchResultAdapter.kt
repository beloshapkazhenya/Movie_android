package com.example.movie.ui.searchresultscreen.searchresultadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movie.R
import com.example.movie.base.baseadapter.BaseListAdapter
import com.example.movie.model.response.moviesearch.SearchItemResponse
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class SearchResultAdapter(
    searchResult: MutableList<SearchItemResponse>
) : BaseListAdapter<SearchItemResponse>(searchResult) {

    private val itemClickSubject = PublishSubject.create<SearchItemResponse>()
    val itemClickObservable: Observable<SearchItemResponse> = itemClickSubject

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder {

        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_card, parent, false)

        return SearchResultViewHolder(itemView, itemClickSubject)
    }
}