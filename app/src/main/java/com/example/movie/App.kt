package com.example.movie

import android.app.Application
import com.example.movie.repository.FavoritesStorage
import com.example.movie.repository.MovieRepository
import io.realm.Realm
import io.realm.RealmConfiguration
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

private lateinit var kodeinStored: DI

class App : Application() {

    companion object {
        var kodein: DI
            get() = kodeinStored
            set(_) {}

        fun isKodeinInitialized() = ::kodeinStored.isInitialized
    }

    private val settingModule = DI.Module("settings_module") {

        bind<FavoritesStorage>() with singleton { FavoritesStorage() }

        bind<MovieRepository>() with singleton { MovieRepository() }
    }

    private fun setRealmConfiguration() {
        Realm.init(applicationContext)
        val realmConfig = RealmConfiguration
            .Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    override fun onCreate() {
        super.onCreate()

        setRealmConfiguration()

        if (::kodeinStored.isInitialized.not())
            kodeinStored = DI {
                import(settingModule)
            }

    }

}