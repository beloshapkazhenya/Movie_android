package com.example.movie.repository

import android.content.Context
import com.example.movie.model.local.MovieDetailsLocal
import io.realm.Realm
import io.realm.RealmConfiguration

class FavoritesStorage(private val context: Context) {
    companion object {
        private const val DATABASE_NAME: String = "favorites_database"
        private const val FIELD_NAME: String = "id"
    }

    private lateinit var realmInstance: Realm

    fun setRealmConfiguration() {
        Realm.init(context)

        val config = RealmConfiguration
            .Builder()
            .allowWritesOnUiThread(true)
            .name(DATABASE_NAME)
            .build()

        realmInstance = Realm.getInstance(config)
    }

    fun saveMovieToFavorite(movieDetailsLocal: MovieDetailsLocal) {

        realmInstance.executeTransaction {

            realmInstance.createObject(MovieDetailsLocal::class.java).apply {
                actors = movieDetailsLocal.actors
                country = movieDetailsLocal.country
                director = movieDetailsLocal.director
                genre = movieDetailsLocal.genre
                plot = movieDetailsLocal.plot
                poster = movieDetailsLocal.poster
                released = movieDetailsLocal.released
                runtime = movieDetailsLocal.runtime
                title = movieDetailsLocal.title
                writer = movieDetailsLocal.writer
                id = movieDetailsLocal.id
            }

        }
    }

    fun getFavoritesList(): ArrayList<MovieDetailsLocal> {

        return ArrayList(
            realmInstance
                .where(MovieDetailsLocal::class.java)
                .findAll()
        )

    }

    fun getMovie(id: String): MovieDetailsLocal? {

        return realmInstance
            .where(MovieDetailsLocal::class.java)
            .equalTo(FIELD_NAME, id)
            .findFirst()

    }

    fun checkMovieInFavoriteList(id: String): Boolean {

        return realmInstance
            .where(MovieDetailsLocal::class.java)
            .equalTo(FIELD_NAME, id)
            .findAll()
            .isEmpty()

    }

    fun deleteFromFavoriteList(id: String) {
        realmInstance.executeTransaction {
            realmInstance
                .where(MovieDetailsLocal::class.java)
                .equalTo(FIELD_NAME, id)
                .findFirst()
                ?.deleteFromRealm()
        }
    }
}