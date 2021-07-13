package com.example.baloot_maryammemarzadeh

import android.app.Application
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp
import java.net.InetAddress

@HiltAndroidApp
class App :Application(){

    override fun onCreate() {
        super.onCreate()
    }

}