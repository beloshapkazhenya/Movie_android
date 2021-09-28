package com.example.movie.repository.movierepository

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DatabaseClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {

        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!
    }
}