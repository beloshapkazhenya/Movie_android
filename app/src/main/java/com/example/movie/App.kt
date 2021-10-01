package com.example.movie

import android.app.Application
import com.example.movie.repository.FavoritesStorage
import com.example.movie.repository.movierepository.MovieRepository
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
        bind<FavoritesStorage>() with singleton {
            FavoritesStorage(
                applicationContext
            )
        }

        bind<MovieRepository>() with singleton { MovieRepository() }
    }

    override fun onCreate() {
        super.onCreate()

        if (::kodeinStored.isInitialized.not())
            kodeinStored = DI {
                import(settingModule)
            }

    }

}