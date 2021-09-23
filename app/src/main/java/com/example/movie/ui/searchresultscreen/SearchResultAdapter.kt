package com.example.movie.ui.searchresultscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.response.Search

class SearchResultAdapter(
    private val context: Context,
    private val searchResult: ArrayList<Search>
) :
    RecyclerView.Adapter<SearchResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_card, parent, false)
        return SearchResultViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val searchResultItem = searchResult[position]
        Glide.with(context).load(searchResultItem.Poster).into(holder.poster)
        holder.movieTitle.text = searchResultItem.Title
    }

    override fun getItemCount(): Int {
        return searchResult.size
    }

}