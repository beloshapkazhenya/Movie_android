package com.example.movie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.ui.searchscreen.SearchActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    companion object {
        private const val DELAY: Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timer().schedule(DELAY) {
            startActivity(
                Intent(applicationContext, SearchActivity::class.java)
            )
            finish()
        }

    }
}