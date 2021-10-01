package com.example.movie.ui.favoritesscreen.favoritesadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.model.local.MovieDetailsLocal

class FavoritesAdapter(
    private val context: Context,
    private val favorites: ArrayList<MovieDetailsLocal>,
    private val onMovieCardClick: (imdbID: String) -> Unit
) : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesViewHolder {

        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.movie_card, parent, false)

        return FavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FavoritesViewHolder,
        position: Int
    ) {
        val favoritesItem = favorites[position]

        Glide
            .with(context)
            .load(favoritesItem.poster)
            .into(holder.poster)

        holder.movieTitle.text = favoritesItem.title

        holder.movieCard.setOnClickListener {
            onMovieCardClick(favoritesItem.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

}