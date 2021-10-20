package com.example.movie.base.safeonclicklistener

import android.os.SystemClock
import android.view.View

fun View.setSafeOnClickListener(debounceTime: Long = 600, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else {
                action()
                lastClickTime = SystemClock.elapsedRealtime()
            }
        }
    })
}