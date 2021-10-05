package com.example.movie.repository

import com.example.movie.model.local.MovieDetailsLocal
import io.realm.Realm

class FavoritesStorage {
    companion object {
        private const val FIELD_NAME: String = "id"
    }

    fun saveMovieToFavorite(movieDetailsLocal: MovieDetailsLocal) {

        Realm.getDefaultInstance().execute {
            it.insertOrUpdate(movieDetailsLocal)
        }

    }

    fun getFavoritesList(): MutableList<MovieDetailsLocal> {
        Realm.getDefaultInstance().use { realm ->
            val favoriteList = realm
                .where(MovieDetailsLocal::class.java)
                .findAll()
                .toMutableList()

            return realm.copyFromRealm(favoriteList)
        }

    }

    fun checkMovieInFavoriteList(id: String): Boolean {

        return Realm.getDefaultInstance()
            .where(MovieDetailsLocal::class.java)
            .equalTo(FIELD_NAME, id)
            .findAll()
            .isEmpty()

    }

    fun deleteFromFavoriteList(id: String) {

        Realm.getDefaultInstance().execute {
            it
                .where(MovieDetailsLocal::class.java)
                .equalTo(FIELD_NAME, id)
                .findFirst()
                ?.deleteFromRealm()
        }
    }

    private fun Realm.execute(block: (realm: Realm) -> Unit) {
        this.use {
            it.executeTransaction { realm ->
                block(realm)
            }
        }
    }
}