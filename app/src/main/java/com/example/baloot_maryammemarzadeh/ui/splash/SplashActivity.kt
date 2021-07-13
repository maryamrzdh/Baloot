package com.example.baloot_maryammemarzadeh.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.baloot_maryammemarzadeh.ui.MainActivity
import com.example.baloot_maryammemarzadeh.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (isFinishing) {
                return@postDelayed
            }
                startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }
}